package com.program;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoard implements KeyListener {

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int c = keyEvent.getKeyCode();
        switch (c){
            case KeyEvent.VK_UP:
                //if()
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
