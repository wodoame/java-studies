The **Java NIO (New Input/Output)** classes, introduced in Java 1.4, provide a set of features for **high-performance, non-blocking I/O operations** primarily centered around **buffers, channels, and selectors**. NIO is designed to overcome some of the limitations of the traditional Java I/O (often called "blocking I/O" or "Old I/O").

---

## 💻 Key Components of Java NIO

The core of Java NIO revolves around three main components:

### 1. **Channels (Interfaces)**

*   **What they are:** Channels are the connection pathways for I/O operations. They are analogous to streams in Old I/O, but with key differences. They represent an open connection to an entity like a hardware device, a network socket, or a file.
*   **Key Difference from Streams:** Streams in Old I/O are one-way (either input or output). Channels are typically **two-way** (can read and write).
*   **Non-Blocking Capability:** Channels can be set to **non-blocking** mode, which is crucial for high scalability (see **Selectors** below).
*   **Common Implementations:** $FileChannel$, $SocketChannel$, $ServerSocketChannel$, $DatagramChannel$.

### 2. **Buffers (Abstract Classes/Concrete Implementations)**

*   **What they are:** Buffers are fixed-size blocks of memory used to hold data (primitives) for reading from and writing to a channel. **All data transfer in NIO must pass through a buffer.**
*   **How they work:** When reading data, you read from the channel **into** the buffer. When writing data, you write from the buffer **out** to the channel.
*   **State:** A buffer tracks its state using three key values:
    *   $Position$: The index of the next element to be read or written.
    *   $Limit$: The index of the first element that *should not* be read or written.
    *   $Capacity$: The maximum number of elements the buffer can hold (fixed).
*   **Common Implementations:** $ByteBuffer$ (most common), $CharBuffer$, $IntBuffer$, $DoubleBuffer$, etc.

### 3. **Selectors (Abstract Class)**

*   **What they are:** A Selector allows a single thread to manage multiple channels (and thus multiple network connections).
*   **How they work:** The thread registers several $SelectableChannel$ objects (like $SocketChannel$ or $ServerSocketChannel$) with the $Selector$. The $Selector$ can then check which registered channels are "ready" for an I/O event (e.g., ready to read data, ready to accept a new connection).
*   **Benefit:** This enables **Non-Blocking I/O (NIO)**. Instead of having one thread per connection (which is inefficient for thousands of connections), a few threads can handle thousands of connections because they only process a connection when it has data ready, rather than blocking while waiting for data. This is the basis for high-concurrency servers.

---

## ⚡ NIO vs. Traditional I/O

| Feature | Traditional I/O (Old I/O) | Java NIO (New I/O) |
| :--- | :--- | :--- |
| **I/O Model** | **Blocking**. A thread is halted (blocked) until data is read or written. | **Non-Blocking**. A thread can check if data is ready and, if not, move on to other tasks. |
| **Data Transfer** | **Stream-based**. Data is read/written one or a few bytes at a time, directly from the OS to the program. | **Buffer-centric**. Data is first moved from the OS into a buffer, then processed from the buffer. |
| **Connection** | **Streams** (one-way). | **Channels** (typically two-way). |
| **Scalability** | Low to moderate. Requires one thread per active connection. | High. A single thread can manage thousands of connections via **Selectors**. |

### Non-Blocking I/O Analogy

Imagine a server managing many clients:

*   **Traditional I/O (Blocking):** A waiter is assigned to a single table. They stand there and wait until the customer is ready to order or needs something, doing nothing else (blocked).
*   **Java NIO (Non-Blocking):** One head waiter ($Selector$) monitors many tables ($Channels$). The waiter doesn't stand at any single table; they only go to a table when a customer rings a bell ($I/O Event$), fulfilling the request, and then immediately goes back to monitoring all the tables. This allows one person to efficiently serve many tables.

## 💾 Example 1: FileChannel and ByteBuffer (File Copy)

This example shows the fundamental NIO pattern: reading data from a file ($FileChannel$) into a memory block ($ByteBuffer$), and then writing it out to another file.

```java
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioFileCopy {
    public static void main(String[] args) throws IOException {
        
        // 1. Get Channels
        // The first channel is for reading the source file
        RandomAccessFile sourceFile = new RandomAccessFile("source.txt", "r");
        FileChannel inChannel = sourceFile.getChannel();

        // The second channel is for writing to the destination file
        RandomAccessFile destFile = new RandomAccessFile("dest.txt", "rw");
        FileChannel outChannel = destFile.getChannel();
        
        // 2. Create Buffer
        // Allocate a ByteBuffer of 48 bytes (size can be anything)
        ByteBuffer buffer = ByteBuffer.allocate(48); 
        
        // 3. Loop and Transfer Data
        // Read the first chunk of data into the buffer
        while(inChannel.read(buffer) != -1) {
            
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
}
```

-----

## ⚙️ Example 2: ByteBuffer State Transitions

This snippet clearly illustrates the lifecycle of a $ByteBuffer$ using its core methods: $put()$, $flip()$, $get()$, and $clear()$.

| Step | Action | Description | $Position$ | $Limit$ |
| :--- | :--- | :--- | :--- | :--- |
| **Initial** | `ByteBuffer buffer = ByteBuffer.allocate(10);` | Buffer is empty and ready to be filled (written). | 0 | 10 |
| **Write** | `buffer.put((byte)1); buffer.put((byte)2);` | Two bytes are written. | 2 | 10 |
| **Read Prep** | `buffer.flip();` | Switches to reading mode. The data written (from $0$ to $2$) is now available to be read. | 0 | 2 |
| **Read** | `byte b1 = buffer.get();` | One byte is read. | 1 | 2 |
| **Reuse** | `buffer.clear();` | Switches back to writing mode, making the whole buffer capacity available for new data. | 0 | 10 |

```java
// Assuming a capacity of 10 bytes
ByteBuffer buffer = ByteBuffer.allocate(10);

// 1. Write Data (Putting)
buffer.put((byte)10); // Position is 1
buffer.put((byte)20); // Position is 2
System.out.println("After Puts: Position=" + buffer.position() + ", Limit=" + buffer.limit()); 
// Output: After Puts: Position=2, Limit=10

// 2. Flip (Ready for Reading)
buffer.flip(); 
// Position is reset to 0, Limit is set to the old Position (2)
System.out.println("After Flip: Position=" + buffer.position() + ", Limit=" + buffer.limit()); 
// Output: After Flip: Position=0, Limit=2

// 3. Read Data (Getting)
byte data = buffer.get(); // Position moves to 1
System.out.println("Read Data: " + data);
System.out.println("After Get: Position=" + buffer.position() + ", Limit=" + buffer.limit()); 
// Output: After Get: Position=1, Limit=2

// 4. Clear (Ready for New Writing)
buffer.clear(); 
// Position resets to 0, Limit resets to Capacity (10)
System.out.println("After Clear: Position=" + buffer.position() + ", Limit=" + buffer.limit());
// Output: After Clear: Position=0, Limit=10
```

-----

## 🌐 Example 3: Non-Blocking I/O with Selector (Simplified Server)

This is the most advanced pattern, demonstrating how a **Selector** handles multiple connections ($ServerSocketChannel$ and $SocketChannel$) using a single thread.

```java
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NioServer {
    public static void main(String[] args) throws IOException {
        
        // 1. Create Selector and ServerSocketChannel
        Selector selector = Selector.open();
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        
        // Configure for non-blocking mode
        serverChannel.configureBlocking(false); 
        serverChannel.bind(new InetSocketAddress(9999));

        // 2. Register the Server Channel
        // Register the server channel with the selector for ACCEPT events
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("Server started on port 9999 (Non-Blocking)");

        // 3. Main Event Loop
        while (true) {
            // Select blocks until at least one registered channel is ready for an I/O event
            selector.select(); 

            // Get the keys of the ready channels
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                
                // Process the event
                if (key.isAcceptable()) {
                    // A new connection is ready to be accepted
                    handleAccept(key, selector); 
                } else if (key.isReadable()) {
                    // A client socket has data ready to be read
                    handleRead(key); 
                }
                
                // Remove the key from the set to avoid processing it again
                keyIterator.remove(); 
            }
        }
    }

    private static void handleAccept(SelectionKey key, Selector selector) throws IOException {
        ServerSocketChannel server = (ServerSocketChannel) key.channel();
        // Accept the new connection
        SocketChannel clientChannel = server.accept(); 
        
        if (clientChannel != null) {
            clientChannel.configureBlocking(false);
            // Register the new client channel for READ events
            clientChannel.register(selector, SelectionKey.OP_READ); 
            System.out.println("Accepted connection from " + clientChannel.getRemoteAddress());
        }
    }

    private static void handleRead(SelectionKey key) throws IOException {
        SocketChannel clientChannel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(256);
        
        // Read data into the buffer
        int bytesRead = clientChannel.read(buffer); 

        if (bytesRead > 0) {
            buffer.flip();
            // Simple echo back mechanism
            clientChannel.write(buffer); 
        } else if (bytesRead == -1) {
            // Client closed connection
            clientChannel.close(); 
            System.out.println("Connection closed by client.");
        }
    }
}
```

Would you like a more detailed explanation of the $flip()$ and $clear()$ methods on the $ByteBuffer$?
