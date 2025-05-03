/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Converter;

/**
 *
 * @author Daza_
 */
public class ColorConverter {
    
    public static float[] rgbToHsv(int r, int g, int b) {
        float[] hsv = new float[3];
        r = clamp(r, 0, 255);
        g = clamp(g, 0, 255);
        b = clamp(b, 0, 255);
        
        float fr = r / 255f, fg = g / 255f, fb = b / 255f;
        float max = Math.max(Math.max(fr, fg), fb);
        float min = Math.min(Math.min(fr, fg), fb);
        float delta = max - min;
        
        if(delta == 0) {
            hsv[0] = 0;
        } else if(max == fr) {
            hsv[0] = (60 * ((fg - fb)/delta) + 360) % 360;
        } else if(max == fg) {
            hsv[0] = (60 * ((fb - fr)/delta) + 120);
        } else {
            hsv[0] = (60 * ((fr - fg)/delta) + 240);
        }
        
        hsv[1] = (max == 0) ? 0 : (delta / max);
        
        hsv[2] = max;
        
        return hsv;
    }
    
    public static int[] hsvToRgb(float h, float s, float v) {
        h = h % 360;
        s = clamp(s, 0, 1);
        v = clamp(v, 0, 1);
        
        int hi = (int)(h / 60) % 6;
        float f = (h / 60) - hi;
        float p = v * (1 - s);
        float q = v * (1 - f * s);
        float t = v * (1 - (1 - f) * s);
        
        float[] rgb;
        switch(hi) {
            case 0: rgb = new float[]{v, t, p}; break;
            case 1: rgb = new float[]{q, v, p}; break;
            case 2: rgb = new float[]{p, v, t}; break;
            case 3: rgb = new float[]{p, q, v}; break;
            case 4: rgb = new float[]{t, p, v}; break;
            default: rgb = new float[]{v, p, q}; break;
        }
        
        return new int[]{
            Math.round(rgb[0] * 255),
            Math.round(rgb[1] * 255),
            Math.round(rgb[2] * 255)
        };
    }
    
    private static int clamp(int value, int min, int max) {
        return Math.min(Math.max(value, min), max);
    }
    
    private static float clamp(float value, float min, float max) {
        return Math.min(Math.max(value, min), max);
    }
}
