package org.calrissian.restdoclet.model.method.description;

import org.junit.Ignore;
import org.junit.Test;

/**
 * @author dgroup
 * @since  27.01.2016
 */
public class SyntaxHighlightingTest {

    @Test(expected = IllegalArgumentException.class)
    public void nullToSingleArgumentConstructor(){
        new SyntaxHighlighting(null);
    }

    @Test
    @Ignore(value = "Implementation required")
    public void singleArgumentConstructor(){

    }

    @Test
    @Ignore(value = "Implementation required")
    public void badParametersToConstructorWithMultiplyArguments(){

    }

    @Test
    @Ignore(value = "Implementation required")
    public void highlightJSON(){

    }
}