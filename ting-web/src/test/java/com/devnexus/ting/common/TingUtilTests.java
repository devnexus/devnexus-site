/**
 *
 */
package com.devnexus.ting.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.pegdown.PegDownProcessor;

/**
 * @author Gunnar Hillert
 *
 *
 */
public class TingUtilTests {

    @Test
    public void test() {

        String markdownSource = "I am **bold**.";

        PegDownProcessor markdownProcessor = TingUtil.getMarkDownProcessor();
        assertNotNull(markdownProcessor);

        String resultingHtml = markdownProcessor.markdownToHtml(markdownSource);

        assertEquals("Resulting HTML did not match.", "<p>I am <strong>bold</strong>.</p>", resultingHtml);
    }

}
