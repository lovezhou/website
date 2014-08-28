/**
 * @(#)CmdProcess.java Copyright 2010 jointown, Inc. All rights reserved.
 */
package com.jessrun.platform.util.security;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * description
 * 
 * @author luoyifan
 * @version 1.0,2010-12-1
 */
public class CmdProcess {

    private static final Logger log            = LoggerFactory.getLogger(CmdProcess.class);
    private String              cmdString;
    private int                 timeout;
    private Process             process;

    private static final String DEFAULT_ENCODE = "GBK";

    private String              encode;

    public CmdProcess(String cmdString, int timeout){
        this(cmdString, timeout, DEFAULT_ENCODE);
    }

    public CmdProcess(String cmdString, int timeout, String encode){
        this.cmdString = cmdString;
        this.timeout = timeout;
        this.encode = encode;
    }

    public int execute() {
        int status = -1;
        Runtime runtime = Runtime.getRuntime();
        try {
            process = runtime.exec(cmdString);
            log.info("exec : " + cmdString);
            TimeoutThread timeoutThread = new TimeoutThread(process);
            timeoutThread.start();
            doExecute();
            status = process.waitFor();
            timeoutThread.cancel();

        } catch (IOException e) {
            log.error("com.jzt.platform.util.security.CmdProcess.execute()", e);
        } catch (InterruptedException e) {
            log.error("com.jzt.platform.util.security.CmdProcess.execute()", e);
        }
        return status;
    }

    private void doExecute() {
        InputStream err = process.getErrorStream();
        InputStream in = process.getInputStream();
        BufferedReader errReader = null;
        BufferedReader inReader = null;
        try {
            errReader = new BufferedReader(new InputStreamReader(err, encode));
            inReader = new BufferedReader(new InputStreamReader(in, encode));
        } catch (UnsupportedEncodingException e) {
            log.error("com.jzt.platform.util.security.CmdProcess.doExecute()", e);
            throw new RuntimeException(e);

        }

        String inLine = null;
        String errLine = null;
        try {
            while ((inLine = inReader.readLine()) != null) {
                log.info(inLine);
            }
            while ((errLine = errReader.readLine()) != null) {
                log.error(errLine);
            }
        } catch (IOException e) {
            log.error("com.jzt.platform.util.security.CmdProcess.doExecute()", e);
        }
    }

    private class TimeoutThread extends Thread {

        private Process process;

        private boolean cancel;

        private TimeoutThread(Process process){
            super();
            this.process = process;

            this.setDaemon(true);
        }

        public synchronized void cancel() {
            cancel = true;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(timeout);
                if (!cancel) process.destroy();
            } catch (InterruptedException e) {
                log.error("com.jzt.platform.util.security.CmdProcess.TimeoutThread.run()", e);
            }
        }

    }
}
