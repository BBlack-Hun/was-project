package org.example;


import org.example.calculate.domain.Calculator;
import org.example.calculate.domain.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CustomWebApplicationServer {

    private final int port;

    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    private static final Logger logger = LoggerFactory.getLogger(CustomWebApplicationServer.class);

    public CustomWebApplicationServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("[CustomWebApplicationServer]] started {} port", port);

            Socket clientSocket;
            logger.info("[CustomWebApplicationServer]] waiting for client.");

            while ((clientSocket = serverSocket.accept()) != null) {
                logger.info("[CustomWebApplicationServer]] client connected!");

                /**
                 * Step2 - 사용자 요청이 들어올 때마다 Thread를 새로생성하여 사용자 요청을 처리하도록 한다.
                 * 제한 없이 생성하게 된다면, 서버 리소스를 과하게 사용하게 되어 서버가 다운될 경우가 발생할 수 있다.
                 */
//                new Thread(new ClientRequestHandler(clientSocket)).start();
                /**
                 * Step3 - Thread pool을 적용해 안정적인 서비스가 가능하도록 한다.
                 */
                executorService.execute(new ClientRequestHandler(clientSocket));

            }
        }
    }
}
