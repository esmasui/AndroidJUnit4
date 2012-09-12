/*
 * Copyright (C) 2012 uPhyca Inc.
 * 
 * Base on previous work by
 * Copyright (C) 2010 Diego Torres Milano
 *
 * Base on previous work by
 * Copyright (C) 2007 Hugo Visser
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS,WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */
package com.uphyca.testing;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import android.annotation.TargetApi;
import android.os.Bundle;

/**
 * This test runner creates an xml in the files directory of
 * the application under test. The output is compatible with
 * that of the junitreport ant task, the format that is
 * understood by Hudson. Currently this implementation does not
 * implement the all aspects of the junitreport format, but
 * enough for Hudson to parse the test results.
 */
public class JUnit4XMLInstrumentationTestRunner extends JUnit4InstrumentationTestRunner {

    private Writer mWriter;
    private XmlSerializer mTestSuiteSerializer;
    private long mTestStarted;

    /**
     * Output file name.
     */
    private String mOutFileName;

    private static final String XML_OUT = "xml";

    /**
     * Outfile argument name.
     * This argument can be passed to the instrumentation using
        <code>-e</code>.
     */
    private static final String OUT_FILE_ARG = "outfile";

    /**
     * Default output file name.
     */
    private static final String OUT_FILE_DEFAULT = "test-results.xml";

    private boolean getBooleanArgument(Bundle arguments,
                                       String tag) {
        String tagString = arguments.getString(tag);
        return tagString != null && Boolean.parseBoolean(tagString);
    }

    private boolean mXml;

    @Override
    public void onCreate(Bundle arguments) {
        if (arguments != null) {
            mXml = getBooleanArgument(arguments,
                                      XML_OUT);
            if (mXml) {
                mOutFileName = arguments.getString(OUT_FILE_ARG);
                if (mOutFileName == null) {
                    mOutFileName = OUT_FILE_DEFAULT;
                }
            }
        }
        super.onCreate(arguments);
    }

    @TargetApi(8)
    @Override
    public void onStart() {
        if (mXml) {
            try {
                File dir = getTargetContext().getExternalFilesDir(null);
                if (dir == null) {
                    dir = getTargetContext().getFilesDir();
                }
                final File outFile = new File(dir, mOutFileName);
                startJUnitOutput(new FileWriter(outFile));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        super.onStart();
    }

    void startJUnitOutput(Writer writer) {
        try {
            mWriter = writer;
            mTestSuiteSerializer = newSerializer(mWriter);
            mTestSuiteSerializer.startDocument(null,
                                               null);
            mTestSuiteSerializer.startTag(null,
                                          "testsuites");
            mTestSuiteSerializer.startTag(null,
                                          "testsuite");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private XmlSerializer newSerializer(Writer writer) {
        try {
            XmlPullParserFactory pf = XmlPullParserFactory.newInstance();
            XmlSerializer serializer = pf.newSerializer();
            serializer.setOutput(writer);
            return serializer;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendStatus(int resultCode,
                           Bundle results) {
        super.sendStatus(resultCode,
                         results);

        if (!mXml) {
            return;
        }

        switch (resultCode) {
        case REPORT_VALUE_RESULT_ERROR:
        case REPORT_VALUE_RESULT_FAILURE:
        case REPORT_VALUE_RESULT_OK:
            try {
                recordTestResult(resultCode,
                                 results);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            break;
        case REPORT_VALUE_RESULT_START:
            recordTestStart(results);
        default:
            break;
        }
    }

    void recordTestStart(Bundle results) {
        mTestStarted = System.currentTimeMillis();
    }

    void recordTestResult(int resultCode,
                          Bundle results) throws IOException {
        float time = (System.currentTimeMillis() - mTestStarted) / 1000.0f;
        String className = results.getString(REPORT_KEY_NAME_CLASS);
        String testMethod = results.getString(REPORT_KEY_NAME_TEST);
        String stack = results.getString(REPORT_KEY_STACK);
        int current = results.getInt(REPORT_KEY_NUM_CURRENT);
        int total = results.getInt(REPORT_KEY_NUM_TOTAL);
        mTestSuiteSerializer.startTag(null,
                                      "testcase");
        mTestSuiteSerializer.attribute(null,
                                       "classname",
                                       className);
        mTestSuiteSerializer.attribute(null,
                                       "name",
                                       testMethod);
        if (resultCode != REPORT_VALUE_RESULT_OK) {
            mTestSuiteSerializer.startTag(null,
                                          "failure");
            if (stack != null) {
                String reason = stack.substring(0,
                                                stack.indexOf('\n'));
                String message = "";
                int index = reason.indexOf(':');
                if (index > -1) {
                    message = reason.substring(index + 1);
                    reason = reason.substring(0,
                                              index);
                }
                mTestSuiteSerializer.attribute(null,
                                               "message",
                                               message);
                mTestSuiteSerializer.attribute(null,
                                               "type",
                                               reason);
                mTestSuiteSerializer.text(stack);
            }
            mTestSuiteSerializer.endTag(null,
                                        "failure");
        } else {
            mTestSuiteSerializer.attribute(null,
                                           "time",
                                           String.format("%.3f",
                                                         time));
        }
        mTestSuiteSerializer.endTag(null,
                                    "testcase");
        if (current == total) {
            mTestSuiteSerializer.startTag(null,
                                          "system-out");
            mTestSuiteSerializer.endTag(null,
                                        "system-out");
            mTestSuiteSerializer.startTag(null,
                                          "system-err");
            mTestSuiteSerializer.endTag(null,
                                        "system-err");
            mTestSuiteSerializer.endTag(null,
                                        "testsuite");
            mTestSuiteSerializer.flush();
        }
    }

    @Override
    public void finish(int resultCode,
                       Bundle results) {
        if (mXml) {
            endTestSuites();
        }

        super.finish(resultCode,
                     results);
    }

    void endTestSuites() {
        try {
            if (mTestSuiteSerializer != null) {
                mTestSuiteSerializer.endTag(null,
                                            "testsuites");
                mTestSuiteSerializer.endDocument();
                mTestSuiteSerializer.flush();
            }
            if (mWriter != null) {
                mWriter.flush();
                mWriter.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
