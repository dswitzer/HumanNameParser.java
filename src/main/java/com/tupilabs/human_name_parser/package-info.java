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

/**
 * <p>Human name parser.</p>
 *
 * <p>The {@link com.tupilabs.human_name_parser.HumanNameParserParser} is capable of parsing
 * names and extract leading init letters, first, last and middle name, as well as suffixes and
 * nicknames.</p>
 *
 * <p>The {@link com.tupilabs.human_name_parser.Name} is a object that encapsulates the name
 * {@code String} but that also contains the logic for handling the Regexes.</p>
 *
 * <p>The code organization and logic are mirrored from the PHP original library, so porting
 * improvements is not very complicated, and users comfortable with the prior library won't have
 * a hard time getting used to this Java port.</p>
 *
 * @since 0.1
 */
package com.tupilabs.human_name_parser;