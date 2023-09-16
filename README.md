# ProxyTray

## Introduction

The `ProxyTray` application is a simple Windows desktop application designed to toggle proxy status on Windows systems without the need to do it through Settings.

## Prerequisites

Before installing `ProxyTray`, ensure you have the following prerequisites:

- **Java Runtime Environment (JRE)**: You must have JRE installed on your Windows machine. Download and install it from the [official Oracle website](https://www.oracle.com/java/technologies/javase-jre8-downloads.html) or consider using an open-source JRE distribution like [OpenJDK](https://openjdk.java.net/).

## Installation Steps (With Executable)

Follow these steps to install and run the `ProxyTray` application using an executable (`.exe`) file:

1. **Download the Application**:

   - Download the `ProxyTray.java` source code file.

2. **Compile the Application**:

   - Open a command prompt.

   - Navigate to the directory containing `ProxyTray.java`.

   - Compile the source code using the `javac` command:

     `javac ProxyTray.java`

3. **Create a Launch4j Configuration File**:

   - Download and install Launch4j from the [official website](http://launch4j.sourceforge.net/).

   - Create a configuration file (e.g., `ProxyTrayConfig.xml`) using a text editor and configure it according to your needs.
   <details>
    <summary>Example configuration</summary>
     
    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <launch4jConfig>
      <dontWrapJar>false</dontWrapJar>
      <headerType>gui</headerType>
      <jar>ProxyTray.jar</jar>
      <outfile>ProxyTray.exe</outfile>
      <errTitle></errTitle>
      <cmdLine></cmdLine>
      <chdir>.</chdir>
      <priority>normal</priority>
      <downloadUrl>http://java.com/download</downloadUrl>
      <supportUrl></supportUrl>
      <stayAlive>false</stayAlive>
      <restartOnCrash>false</restartOnCrash>
      <manifest></manifest>
      <icon></icon>
      <classPath>
        <mainClass>ProxyTray</mainClass>
      </classPath>
      <jre>
        <path>C:\Path\to\your\jre\bin\javaw.exe</path>
        <bundledJre64Bit>false</bundledJre64Bit>
        <bundledJreAsFallback>false</bundledJreAsFallback>
        <minVersion>1.8.0</minVersion>
        <maxVersion></maxVersion>
        <jdkPreference>preferJre</jdkPreference>
        <runtimeBits>64/32</runtimeBits>
      </jre>
    </launch4jConfig>

    ```
     
   </details>

4. **Generate the Executable**:

   - Open Launch4j and load your configuration file (`ProxyTrayConfig.xml`).

   - Click the "Build Wrapper" button to generate the `.exe` file.

## Usage

Once the `ProxyTray` application is running, use the system tray icon to control the proxy status:

- **Toggle Proxy Status**: Right-click the system tray icon and select "Enable proxy" or "Disable proxy" to toggle the proxy status.

- **View Proxy Information**: Hover over the system tray icon to view proxy information (when the proxy is enabled).

- **Exit Application**: Right-click the system tray icon and select "Exit" to close the application.

## Important Notes

- This application is specifically designed for Windows systems due to its reliance on the Windows Registry for proxy configuration.
