import net.minecraft.client.main.Main;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Start {
    public static void main(String[] args) {
        String currentDir = System.getProperty("user.dir");
        System.out.println("Current working directory: " + currentDir);

        String osName = System.getProperty("os.name").toLowerCase();
        String osArch = System.getenv("PROCESSOR_ARCHITECTURE");
        if (osArch == null) {
            osArch = System.getenv("PROCESSOR_ARCHITEW6432");
        }
        if (osArch == null) {
            osArch = System.getProperty("os.arch").toLowerCase();
        }
        String nativePath;

        if (osName.contains("win")) {
            nativePath = "natives/windows";
        } else if (osName.contains("mac")) {
            if (osArch.contains("aarch64") || osArch.contains("arm64")) {
                nativePath = "natives/osx_silicon";
            } else {
                nativePath = "natives/osx";
            }
        } else if (osName.contains("nix") || osName.contains("nux")) {
            nativePath = "natives/linux";
        } else {
            throw new UnsupportedOperationException("Unsupported operating system: " + osName);
        }

        String absoluteNativePath = new File(new File(currentDir).getParentFile(), nativePath)
            .getAbsolutePath();
        File nativeDir = new File(absoluteNativePath);

        if (!nativeDir.exists()) {
            System.out.println("Default native library path not found: " + absoluteNativePath);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter a custom path:");
            absoluteNativePath = scanner.nextLine();
            nativeDir = new File(absoluteNativePath);

            if (!nativeDir.exists()) {
                System.err.println("The provided custom path does not exist. Exiting.");
                return;
            }
        }

        System.out.println("Native library path: " + absoluteNativePath);


        System.setProperty("org.lwjgl.librarypath", absoluteNativePath);

        Main.main(concat(new String[]{
                        "--version", "MavenMCP",
                        "--accessToken", "0",
                        "--assetsDir", "assets",
                        "--assetIndex", "1.8",
                        "--userProperties", "{}"},
                args)
        );
    }

    public static <T> T[] concat(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }
}