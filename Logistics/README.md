# Logistics

This `Logistics` project is a simple Java application demonstrating various core concepts, including object serialization, file I/O, and inter-process communication using TCP/IP sockets. It simulates the management and transfer of `TruckExpense` data.

The `Main.java` file orchestrates the primary flow of this application:

1.  **Data Initialization:**
    * It initializes a list of `TruckExpense` objects, each containing details like ID, insurance company, repair costs, and associated `Prices` (for fuel, maintenance, and tolls). This data represents typical logistics expenses.

2.  **Serialization to File:**
    * The `Utils.serialize()` method is used to write this list of `TruckExpense` objects to a binary file named `trucks.dat`. This demonstrates object serialization, allowing complex Java objects to be converted into a byte stream for storage or transmission.

3.  **Network Communication (TCP Example):**
    * The project sets up a **TCP Server** (`TCPServer.java`) that listens for incoming connections on a specified port (e.g., 8080). This server runs in a separate thread to allow the `Main` application to continue.
    * After a brief delay to ensure the server is ready, a **TCP Client** (`TCPClient.java`) connects to this server.
    * The client then reads the `trucks.dat` file and sends its content over the network to the TCP Server. This showcases how data can be transferred between different applications or processes over a network.

    *(Note: The `UDPServer.main` and `UDPClient.main` lines are commented out in `Main.java`, but the project is structured to potentially support UDP communication as well.)*