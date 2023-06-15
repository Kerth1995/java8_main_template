package com.challenge.challenge.service.impl;

import com.challenge.challenge.service.IHackerDetector;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
public class HackerDetectorImpl implements IHackerDetector {
    @Override
    public String parseLine() {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return address.getHostAddress();
    }
}
