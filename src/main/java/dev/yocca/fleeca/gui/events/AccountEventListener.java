/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.yocca.fleeca.gui.events;

import java.util.EventListener;

/**
 *
 * @author mayocca
 */
public interface AccountEventListener extends EventListener {
    void accountEvent(AccountEvent event);
}
