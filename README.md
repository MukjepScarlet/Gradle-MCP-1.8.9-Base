# GradleMCPBase
Gradle MCP for 1.8.9 with Optifine

## How to setup
Clone the repository into whatever folder you want, open the project in Intellij IDEA then sync Gradle. Execute the Gradle task `runClient` (in `minecraft` group).

Now you can start Minecraft!

# How to build

Run the "build" run command in the Gradle tab.

Once you've done that go into the build/libs directory inside the project folder, 
open the jar file with 7-zip or Winrar, now delete the META-INF folder, and I think you know how to
do everything to get it to start in the launcher.

![config_list](https://github.com/AbyssClient/GradleMCPBase/assets/170053471/9d558995-ec49-4ea3-b066-35cedbd78338)

Credits to Markelectro for the marCloud repo

## How to add library

You can include your external library with Gradle Configuration `library`. 

If you want to upgrade one of `gameLibrary`, please add the newer version with `library`.
Otherwise you should edit the `version.json` file.

## How to add JVM Language (Kotlin, Scala, etc.)

1. Add the Gradle plugin for it.
2. Include its standard library in shadowJar (important!).
3. Make sure its bytecode has the same version with each other.
4. Let's go!
