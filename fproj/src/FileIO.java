// Java NIO (New Input/Output) provides a more efficient way to handle file I/O operations
// compared to the traditional Java I/O (java.io) package.
// NIO introduces several new concepts and classes that enhance performance and scalability,
// especially for applications that require high-speed data processing or handle multiple I/O operations concurrently.
// core components for file I/O in Java
// 1. Channels - They represent an open connection to an entity like a hardware device, a network socket, or a file.
// 2. Buffers - Buffers are fixed-size blocks of memory used to hold data (primitives) for reading from and writing to a channel.
// 3. Selectors - A Selector allows a single thread to manage multiple channels (and thus multiple network connections).

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileIO {

    public static void main(String[] args) {
        writeToFile();
        readFromFile();
    }

    public static void fileCopy() throws IOException {
        // 1. Get Channels
        // The first channel is for reading the source file
        RandomAccessFile sourceFile = new RandomAccessFile("src/source.txt", "r");
        FileChannel inChannel = sourceFile.getChannel();

        // The second channel is for writing to the destination file
        RandomAccessFile destFile = new RandomAccessFile("src/dest.txt", "rw");
        FileChannel outChannel = destFile.getChannel();

        // 2. Create Buffer
        // Allocate a ByteBuffer of 48 bytes (size can be anything)
        ByteBuffer buffer = ByteBuffer.allocate(48);

        // 3. Loop and Transfer Data
        // Read the first chunk of data into the buffer
        while (inChannel.read(buffer) != -1) {

            // a. Prepare for reading (flip)
            // Flip changes the buffer from writing mode to reading mode.
            // It sets LIMIT to the current POSITION, and sets POSITION to 0.
            buffer.flip();

            // b. Write data out
            // Write the buffer's contents to the output channel
            outChannel.write(buffer);

            // c. Prepare for writing again (clear or compact)
            // Clear resets POSITION to 0 and LIMIT to CAPACITY, emptying the buffer conceptually.
            buffer.clear();
        }

        // Close all channels and streams
        sourceFile.close();
        destFile.close();
    }


    public static void readFile() throws IOException {
        Path path = Paths.get("src/source.txt");
        List<String> lines = Files.readAllLines(path);
        for (String line : lines) {
            System.out.println(line);
        }
    }

    // Write to file using BufferedWriter
    static void writeToFile() {
        String[] names = {"Alice", "Bob", "Charlie", "David"};
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/output.txt"));
            writer.write("Hello, World!\n");
            writer.write("Write another line to the file.\n");
            for(String name : names) {
                writer.write(name + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    // read from a class using BufferedReader
    static void readFromFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/output.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
