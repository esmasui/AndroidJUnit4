This is a sample project configured and setup to use Robolectric.  This project shows how to configure
your build file, how to layout your project, sample tests, etc.

There are caveats:

    - This project uses Maven and can be imported into IntelliJ (open the pom.xml) or Eclipse (using M2Eclipse).

    - We recommend building with Maven, but this project can also be built using ant.


INSTALLATION

To install this sample app and run the tests:

    git clone git://github.com/pivotal/RobolectricSample.git
    cd RobolectricSample
    android update project -p .

For more information about how to get projects running in your IDE see:
http://pivotal.github.com/robolectric/quick-start.html


BUILDING WITH MAVEN

Once only*:
    ant maven-setup

To build and run tests:
    mvn clean test

To install the sample app on the emulator:
    mvn package android:deploy


BUILDING WITH ANT

To build and run tests:
    ant clean test

To install the sample app on the emulator:
    ant debug
    adb -e install [-r] <your-project-name>-debug.apk

The project contains a build.xml configured for the layout of the project.  The file extends the standard Android
build.xml modified to support the layout of the project files.  The build.xml file defines a test target.


CONTRIBUTING

We welcome contributions. Please fork and submit pull requests!

* This method leaves your pom.xml in an edited state. If you know the path to the Android SDK (it gets stored as
"sdk.dir" in your local.properties file) you can avoid this by running an alternative ant target and adding
an xml snippet to your ~/.m2/settings.xml file:

    ant maven-install-jars

and make your settings.xml file look something like this:
    <settings>
        <profiles>
            <profile>
                <id>android</id>
                <properties>
                    <android.sdk.path>
                        PATH / TO / THE / ANDROID / SDK
                    </android.sdk.path>
                </properties>
            </profile>
        </profiles>
        <activeProfiles>
            <!--make the profile active all the time -->
            <activeProfile>android</activeProfile>
        </activeProfiles>
    </settings>
