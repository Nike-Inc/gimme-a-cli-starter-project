/*
 * Copyright 2019-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 */

package com.nike.mycli.commands;

import com.beust.jcommander.Parameters;
import com.nike.gimme.a.cli.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Command that outputs the current version of this CLI.
 */
@Component
@Parameters(commandNames = {"--version"}, commandDescription = "Output the version of this CLI to the terminal")
public class VersionCommand implements Command {

    private static final String PROPERTIES_FILE = "mycli.properties";
    private static final String VERSION_PROP_NAME = "mycli.version";

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void execute() throws Exception {
        log.info(getVersion());
    }

    /**
     * Get a string describing the current version of this CLI
     */
    private String getVersion() throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = this.getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE);
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties.getProperty(VERSION_PROP_NAME);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    // ignore
                }
            }
        }
    }
}
