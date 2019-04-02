package com.example.testPoker.model.game;

import org.testng.Assert;
import org.testng.annotations.Test;

public class KindTest {

    @Test
    public void testKind() {
        Assert.assertEquals(Kind.PICAS.kindSymbol(), "♠");
        Assert.assertEquals(Kind.CORAZONES.kindSymbol(), "❤");
        Assert.assertEquals(Kind.TREBOLES.kindSymbol(), "♣");
        Assert.assertEquals(Kind.DIAMANTES.kindSymbol(), "♦");
    }

}