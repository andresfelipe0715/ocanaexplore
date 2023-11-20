package com.proyecto_turismo_ufpso.turismo.purchase.service;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class SerialNumberGenerator {

    private static final int SERIAL_NUMBER_LENGTH = 16;
    private static final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final Random RANDOM = new Random();

    private static Set<String> usedSerialNumbers = new HashSet<>();

    public static String generateUniqueSerialNumber() {
        String serialNumber;
        do {
            serialNumber = generateRandomSerialNumber();
        } while (!usedSerialNumbers.add(serialNumber));
        return serialNumber;
    }

    private static String generateRandomSerialNumber() {
        StringBuilder sb = new StringBuilder(SERIAL_NUMBER_LENGTH);
        for (int i = 0; i < SERIAL_NUMBER_LENGTH; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(randomIndex));
        }
        return sb.toString();
    }
}
