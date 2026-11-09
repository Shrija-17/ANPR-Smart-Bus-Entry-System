package com.smartbus.smartbusgatemanagement.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Service
public class PythonService {

    public String detectPlate() {

        try {

            ProcessBuilder processBuilder = new ProcessBuilder(
                    "python",
                    "plate_detection.py"
            );

            processBuilder.directory(
                    new java.io.File("C:\\Users\\ELCOT\\Desktop\\ANPR-Python")
            );

            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();

            System.out.println("Python process started...");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );

            String line;
            String plateNumber = "";

            while ((line = reader.readLine()) != null) {

                System.out.println(line);

                if (!line.trim().isEmpty()) {
                    plateNumber = line;
                }
            }

            process.waitFor();

            return plateNumber;

        } catch (Exception e) {
            e.printStackTrace();
            return "NOT DETECTED";
        }

    }

}