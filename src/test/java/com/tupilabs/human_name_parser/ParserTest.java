/*
 * The MIT License
 *
 * Copyright (c) 2010-2015 Jason Priem, Bruno P. Kinoshita
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.tupilabs.human_name_parser;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.junit.BeforeClass;
import org.junit.Test;

public class ParserTest {

    private static final Logger LOGGER = Logger.getLogger(ParserTest.class.getName());

    private static File testNames = null;

    @BeforeClass
    public static void setUp() {
        testNames = new File(ParserTest.class.getResource("/testNames.txt").getFile());
    }

    @Test
    public void testAllNames() throws IOException {
        BufferedReader buffer = null;
        FileReader reader = null;

        try {
            reader = new FileReader(testNames);
            buffer = new BufferedReader(reader);

            String line = null;
            while ((line = buffer.readLine()) != null) {
                if (StringUtils.isBlank(line)) {
                    LOGGER.warning("Empty line in testNames.txt");
                    continue;
                }

                String[] tokens = line.split("\\|");
                if (tokens.length != 8) {
                    LOGGER.warning(String.format("Invalid line in testNames.txt: %s", line));
                    continue;
                }

                validateLine(tokens);
            }
        } finally {
            if (reader != null)
                reader.close();
            if (buffer != null)
                buffer.close();
        }
    }

    @Test
    public void title_should_allow_optional_periods() throws IOException {
    	String name = "James Francis Ryan";
    	String fullname = "";
    	String[] expected = {
    	    "PFC"
    	  , "pfc"
    	  , "PFC."
    	  , "pfc."
    	  , "P.F.C."
    	  , "p.f.c."
    	};

    	// test first name first
    	for( int i=0; i < expected.length; i++ ){
    		fullname = expected[i] + " " + name;
            assertEquals(fullname, expected[i], new HumanNameParserParser(fullname).getTitle());
    	}

    	// test first name last
    	name = "Ryan, %s James Francis";
    	for( int i=0; i < expected.length; i++ ){
    		fullname = String.format(name, expected[i]);
            assertEquals(fullname, expected[i], new HumanNameParserParser(fullname).getTitle());
    	}

    }

    @Test
    public void fullfirst_should_return_leading_initial_and_first_name() throws IOException {
    	String name = "C. Björn Roger O'Malley";
    	String expected = "C. Björn";

    	assertEquals(name, expected, new HumanNameParserParser(name).getFullFirst());
    }

    @Test
    public void fullfirst_should_return_only_first_name_when_no_initial() throws IOException {
    	String name = "Björn O'Malley";
    	String expected = "Björn";

    	assertEquals(name, expected, new HumanNameParserParser(name).getFullFirst());
    }

    @Test
    public void fullfirst_should_return_only_leading_initial_when_no_first_name() throws IOException {
    	String name = "B O'Malley";
    	String expected = "B";

    	assertEquals(name, expected, new HumanNameParserParser(name).getFullFirst());
    }

    @Test
    public void lastname_should_return_only_last_name_when_no_suffix() throws IOException {
    	String name = "Björn O'Malley";
    	String expected = "O'Malley";

    	assertEquals(name, expected, new HumanNameParserParser(name).getFullLast());
    }

    @Test
    public void lastname_should_return_last_name_and_suffix() throws IOException {
    	String name = "Björn O'Malley, Jr.";
    	String expected = "O'Malley, Jr.";

    	assertEquals(name, expected, new HumanNameParserParser(name).getFullLast());
    }

    @Test
    public void lastname_should_return_last_name_and_suffix_space_delim() throws IOException {
    	String name = "Björn O'Malley Jr";
    	String expected = "O'Malley Jr";

    	assertEquals(name, expected, new HumanNameParserParser(name).getFullLast());
    }

    /**
     * Validates a line in the testNames.txt file.
     *
     * @param tokens the tokens with leading spaces
     */
    private void validateLine(String[] tokens) {
        String name = tokens[0].trim();

        String title = tokens[1].trim();
        String leadingInit = tokens[2].trim();
        String first = tokens[3].trim();
        String nickname = tokens[4].trim();
        String middle = tokens[5].trim();
        String last = tokens[6].trim();
        String suffix = tokens[7].trim();

        HumanNameParserParser parser = new HumanNameParserParser(name);

        assertEquals(name, title, parser.getTitle());
        assertEquals(name, leadingInit, parser.getLeadingInit());
        assertEquals(name, first, parser.getFirst());
        assertEquals(name, nickname, parser.getNicknames());
        assertEquals(name, middle, parser.getMiddle());
        assertEquals(name, last, parser.getLast());
        assertEquals(name, suffix, parser.getSuffix());
    }

}
