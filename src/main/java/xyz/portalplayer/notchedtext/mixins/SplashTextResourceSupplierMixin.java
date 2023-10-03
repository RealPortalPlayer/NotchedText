// Purpose: 
// Created on: 10/2/23 @ 9:20 PM

// Copyright (c) 2022, 2023, PortalPlayer <email@portalplayer.xyz>
// Licensed under MIT <https://opensource.org/licenses/MIT>

package xyz.portalplayer.notchedtext.mixins;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.SplashTextRenderer;
import net.minecraft.client.resource.SplashTextResourceSupplier;
import net.minecraft.client.session.Session;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Calendar;

@Mixin(SplashTextResourceSupplier.class)
public class SplashTextResourceSupplierMixin {
    @Shadow
    @Final
    private Session session;
    
    @Unique
    private static final SplashTextRenderer HAPPY_BIRTHDAY_EZ = new SplashTextRenderer("Happy birthday, ez!");
    
    @Unique
    private static final SplashTextRenderer HAPPY_BIRTHDAY_NOTCH = new SplashTextRenderer("Happy birthday, Notch!");
    
    @Unique
    private static final SplashTextRenderer HAPPY_BIRTHDAY_ATHNA = new SplashTextRenderer("Happy birthday! I love you! Alex x");
    
    @Inject(method = "get", at = @At("HEAD"), cancellable = true)
    private void getInjected(CallbackInfoReturnable<SplashTextRenderer> cir) {
        int month;
        int day;

        {
            Calendar calendar = Calendar.getInstance();

            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);
        }
        
        if (month == Calendar.NOVEMBER && day == 9) {
            cir.setReturnValue(HAPPY_BIRTHDAY_EZ);
            return;
        }

        if (month == Calendar.JUNE || day == 1) {
            cir.setReturnValue(HAPPY_BIRTHDAY_NOTCH);
            return;
        }

        if (month != Calendar.JULY || day != 26 || session == null || !session.getUsername().equals("athna"))
            return;

        cir.setReturnValue(HAPPY_BIRTHDAY_ATHNA);
    }
}
