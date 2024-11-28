package com.deameyesapps.crayon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.deameyesapps.GameLib.Button;
import com.deameyesapps.GameLib.DrawShape;
import com.deameyesapps.GameLib.DrawText;
import com.deameyesapps.GameLib.GameMode;
import com.deameyesapps.GameLib.MouseClick;
import com.deameyesapps.GameLib.RenderContainer;

import java.util.ArrayList;
import java.util.List;

public class CrayonMode extends GameMode {

    Button zoomInButton;
    Button zoomOutButton;
    Button importButton;
    Button exportButton;
    Button[] colors;
    Button[][] bitmapPixels;
    Button currentPixelColor;
    Button addColumnButton;
    Button removeColumnButton;
    Button addRowButton;
    Button removeRowButton;
    Button shiftColumnsLeftButton;
    Button shiftColumnsRightButton;
    Button shiftRowsUpButton;
    Button shiftRowsDownButton;

    public  CrayonMode(){
        int tempWidth = Gdx.graphics.getWidth();
        int tempHeight = Gdx.graphics.getHeight();

        zoomInButton = new Button(tempWidth - 215, tempHeight - 100,100,100, Color.BLACK, Color.LIGHT_GRAY, " +");
        zoomOutButton = new Button(tempWidth - 105,tempHeight - 100,100,100, Color.BLACK, Color.LIGHT_GRAY, " -");
        importButton = new Button(tempWidth - 215, tempHeight - 210, 100, 100, Color.BLACK, Color.LIGHT_GRAY, " I");
        exportButton = new Button(tempWidth - 105, tempHeight - 210, 100, 100, Color.BLACK, Color.LIGHT_GRAY, " X");

        shiftColumnsLeftButton = new Button(0, 0,100,100, Color.BLACK, Color.LIGHT_GRAY, "<<");
        shiftColumnsLeftButton.buttonTimeoutMax = 50;
        addColumnButton = new Button(200, 0,100,100, Color.BLACK, Color.LIGHT_GRAY, "C+");
        removeColumnButton = new Button(400, 0,100,100, Color.BLACK, Color.LIGHT_GRAY, "C-");
        shiftColumnsRightButton = new Button(600, 0,100,100, Color.BLACK, Color.LIGHT_GRAY, ">>");
        shiftColumnsRightButton.buttonTimeoutMax = 50;

        shiftRowsUpButton = new Button(tempWidth - 325, tempHeight - 100,100,100, Color.BLACK, Color.LIGHT_GRAY, "/^\\");
        shiftRowsUpButton.buttonTimeoutMax = 50;
        addRowButton = new Button(tempWidth - 325, tempHeight - 320,100,100, Color.BLACK, Color.LIGHT_GRAY, "R+");
        removeRowButton = new Button(tempWidth - 325, tempHeight - 540,100,100, Color.BLACK, Color.LIGHT_GRAY, "R-");
        shiftRowsDownButton = new Button(tempWidth - 325, tempHeight - 760,100,100, Color.BLACK, Color.LIGHT_GRAY, "\\V/");
        shiftRowsDownButton.buttonTimeoutMax = 50;
        colors = new Button[16];

        colors[0] = new Button(tempWidth - 215, tempHeight - 320, 100, 100, Color.BLACK, Color.BLACK, "");
        colors[1] = new Button(tempWidth - 215, tempHeight - 430, 100, 100, Color.BLACK, Color.MAROON, "");
        colors[2] = new Button(tempWidth - 215, tempHeight - 540, 100, 100, Color.BLACK, Color.GREEN, "");
        colors[3] = new Button(tempWidth - 215, tempHeight - 650, 100, 100, Color.BLACK, Color.OLIVE, "");
        colors[4] = new Button(tempWidth - 215, tempHeight - 760, 100, 100, Color.BLACK, Color.NAVY, "");
        colors[5] = new Button(tempWidth - 215, tempHeight - 870, 100, 100, Color.BLACK, Color.PURPLE, "");
        colors[6] = new Button(tempWidth - 215, tempHeight - 980, 100, 100, Color.BLACK, Color.TEAL, "");
        colors[7] = new Button(tempWidth - 215, tempHeight - 1090, 100, 100, Color.BLACK, Color.LIGHT_GRAY, "");
        colors[8] = new Button(tempWidth - 105, tempHeight - 320, 100, 100, Color.BLACK, Color.GRAY, "");
        colors[9] = new Button(tempWidth - 105, tempHeight - 430, 100, 100, Color.BLACK, Color.RED, "");
        colors[10] = new Button(tempWidth - 105, tempHeight - 540, 100, 100, Color.BLACK, Color.valueOf("32CD32"), "");
        colors[11] = new Button(tempWidth - 105, tempHeight - 650, 100, 100, Color.BLACK, Color.YELLOW, "");
        colors[12] = new Button(tempWidth - 105, tempHeight - 760, 100, 100, Color.BLACK, Color.BLUE, "");
        colors[13] = new Button(tempWidth - 105, tempHeight - 870, 100, 100, Color.BLACK, Color.MAGENTA, "");
        colors[14] = new Button(tempWidth - 105, tempHeight - 980, 100, 100, Color.BLACK, Color.CYAN, "");
        colors[15] = new Button(tempWidth - 105, tempHeight - 1090, 100, 100, Color.BLACK, Color.WHITE, "");

        currentPixelColor = new Button(tempWidth - 215, tempHeight - 1305, 205, 205, Color.BLACK, Color.BLACK, "");

        int xWidth = (tempWidth/ 105) - 3;
        int yHeight = (tempHeight/105) - 1;
        bitmapPixels = new Button[xWidth][yHeight];
        for(int i = 0; i < bitmapPixels.length; i++)
        {
            for(int j = 0; j < bitmapPixels[0].length; j++)
            {
                bitmapPixels[i][j] = new Button(i * 105, tempHeight - (105 * j) - 105, 100, 100, Color.BLACK, new Color(16777215), "");
            }
        }
    }

    public  void performImport()
    {
        ArrayList<ArrayList<Color>> colors = new ArrayList<ArrayList<Color>>();
        String importText = Gdx.app.getClipboard().getContents();
        importText = importText.replace("\n","");
        importText = importText.replace("\t","");
        importText = importText.replace(" ","");
        importText = importText.substring(importText.indexOf("{") + 1); //strip beginning bracket
        while(importText.length() > 0 && importText.charAt(0) != '}')//outermost incapsulation
        {
            if(importText.charAt(0) == '{')
            {
                ArrayList<Color> tempLine = new ArrayList<Color>();
                importText = importText.substring(1);
                Boolean eol = false;
                //while(importText.charAt(0) != '}')//x level incapsulation
                while(!eol)
                {
                    int splitLocationComma = importText.indexOf(',');
                    int splitLocationEndBrace = importText.indexOf('}');
                    int splitLocation = Math.min(splitLocationComma, splitLocationEndBrace);
                    if(splitLocation == -1)//if not found use other value
                        splitLocation = Math.max(splitLocationComma, splitLocationEndBrace);
                    if(splitLocation == splitLocationEndBrace)
                        eol  = true;
                    String tempStrInt = importText.substring(0, splitLocation);
                    int tempInt = Integer.parseInt(tempStrInt);
                    tempLine.add(new Color(tempInt));
                    importText = importText.substring(splitLocation + 1);
                    if(eol)
                        importText = importText.substring(1);
                }
                colors.add(tempLine);
            }
        }
        for(int i = 0; i < colors.size();i++)
        {
            ArrayList<Color> colorLine = colors.get(i);
            for(int j = 0; j < colorLine.size();j++)
            {
                bitmapPixels[i][j].BackColor = colorLine.get(j);
            }
        }
    }

    public void performExport()
    {
        String stringOut = "{";
        for(int i = 0; i < bitmapPixels.length; i++)
        {
            stringOut += "{";
            for(int j = 0; j < bitmapPixels[0].length; j++)
            {
                stringOut += Color.rgba8888(bitmapPixels[i][j].BackColor);

                if(j < bitmapPixels[i].length - 1)
                    stringOut += ",";
            }
            stringOut += "}";
            if(i < bitmapPixels.length - 1)
                stringOut += ",";
        }
        stringOut += "}";
        Gdx.app.getClipboard().setContents(stringOut);
    }

    public  void performDownShift()
    {
        for(int i = 0; i < bitmapPixels.length; i++)
        {
            for(int j = bitmapPixels[i].length - 2; j > -1; j--)
            {
                bitmapPixels[i][j+1].BackColor = bitmapPixels[i][j].BackColor;
            }
        }
        for(int i = 0; i < bitmapPixels.length;i++)
        {
            bitmapPixels[i][0].BackColor = currentPixelColor.BackColor;
        }
    }

    public void performUpShift()
    {
        for(int i = 0; i < bitmapPixels.length; i++)
        {
            for(int j = 1; j < bitmapPixels[i].length; j++)
            {
                bitmapPixels[i][j-1].BackColor = bitmapPixels[i][j].BackColor;
            }
        }
        for(int i = 0; i < bitmapPixels.length;i++)
        {
            bitmapPixels[i][bitmapPixels[i].length - 1].BackColor = currentPixelColor.BackColor;
        }
    }

    public void performLeftShift()
    {
        for(int i = 0; i < bitmapPixels.length - 1; i++)
        {
            for(int j = 0; j < bitmapPixels[i].length; j++)
            {
                bitmapPixels[i][j].BackColor = bitmapPixels[i+ 1][j].BackColor;
            }
        }
        for(int i = 0; i < bitmapPixels[0].length;i++)
        {
            bitmapPixels[bitmapPixels.length - 1][i].BackColor = currentPixelColor.BackColor;
        }
    }

    public void performRightShift()
    {
        for(int i = bitmapPixels.length - 1; i > 0; i--)
        {
            for(int j = 0; j < bitmapPixels[i].length; j++)
            {
                bitmapPixels[i][j].BackColor = bitmapPixels[i - 1][j].BackColor;
            }
        }
        for(int i = 0; i < bitmapPixels[0].length;i++)
        {
            bitmapPixels[0][i].BackColor = currentPixelColor.BackColor;
        }
    }

    public boolean mouseClick(List<MouseClick> clicks)
    {
        if(importButton.mouseClick(clicks))
        {performImport();}
        if(exportButton.mouseClick(clicks))
        {performExport();}

        if(shiftRowsDownButton.mouseClick(clicks))
        {performDownShift();}
        if(shiftRowsUpButton.mouseClick(clicks))
        {performUpShift();}

        if(shiftColumnsLeftButton.mouseClick(clicks))
        {performLeftShift(); }
        if(shiftColumnsRightButton.mouseClick(clicks))
        {performRightShift();}

        //check for colors clicked:
        for(int i = 0; i < colors.length;i++)
        {
            if(colors[i].mouseClick(clicks))
            {
                currentPixelColor.BackColor = colors[i].BackColor;
                currentPixelColor.mouseRelease();
                colors[i].mouseRelease();
            }
        }

        for(int i = 0; i < bitmapPixels.length;i++)
        {
            for(int j = 0; j < bitmapPixels[i].length;j++)
            {
                if(bitmapPixels[i][j].mouseClick(clicks))
                {
                    bitmapPixels[i][j].BackColor = currentPixelColor.BackColor;
                    bitmapPixels[i][j].mouseRelease();
                }
            }
        }
        return false;
    }

    @Override
    public void mouseRelease() {

    }

    @Override
    public boolean checkClicks(List<MouseClick> clicks) {

        return false;
    }

    @Override
    //public void render(PerspectiveCamera camera, List<ModelInstance> modelInstances, List<DrawShape> lowerDraw, List<DrawText> text, List<DrawShape> upperDraw)
    public boolean render(RenderContainer rc)
    {
        zoomInButton.render(rc);
        zoomOutButton.render(rc);
        importButton.render(rc);
        exportButton.render(rc);
        currentPixelColor.render(rc);

        shiftRowsUpButton.render(rc);
        addRowButton.render(rc);
        removeRowButton.render(rc);
        shiftRowsDownButton.render(rc);

        shiftColumnsLeftButton.render(rc);
        addColumnButton.render(rc);
        removeColumnButton.render(rc);
        shiftColumnsRightButton.render(rc);

        for(int i = 0; i < colors.length; i++)
        {
            colors[i].render(rc);
        }

        for(int i = 0; i < bitmapPixels.length; i++){
            for(int j = 0; j < bitmapPixels[i].length;j++)
            {
                bitmapPixels[i][j].render(rc);
            }
        }

        mouseClick(rc.clicks);
        return  false;
    }

    @Override
    public void dispose() {

    }
}
