package myTileGame.gfx.light;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.List;

import myTileGame.Handler;
import myTileGame.Utils;
import myTileGame.objects.entites.Entity;

public class LightMap {
	public static final int SCALE = 1;
	private BufferedImage lightMap;
	private List<Light>lights;
	private Handler handler;
	private int[]pixels;
	private float[]lightAtPoint;
	private int width,height;
	private Graphics2D g;
	public LightMap(Handler handler) {
		this.handler = handler;
		width = handler.getGameWidth()/SCALE;
		height = handler.getGameHeight()/SCALE;
		lightMap = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
		pixels = ((DataBufferInt)lightMap.getRaster().getDataBuffer()).getData();
		lights = new ArrayList<Light>();
		lightAtPoint = new float[width*height];
		g = (Graphics2D) lightMap.getGraphics();
	}
	public void addLight( float x,float y,float radius,float lightPower ){
		Light light = new Light(x,y,radius,lightPower);
		lights.add(light);
	}
	public void addLight(Entity e,float radius,float lightPower ){
		Light light = new Light(0,0,radius,lightPower);
		light.focusOnEntity(e);
		lights.add(light);
	}
	public void removeLight(Light light){
		lights.remove(light);
	}
	
	public void tick(){
		for(Light l : lights){
			l.tick();
		}
	}
	private void invertAlpha(){
		for(int i=0;i<width*height;i++){
			Color color = new Color(pixels[i],true);
			pixels[i] = new Color(
					color.getRed(),
					color.getBlue(),
					color.getGreen(),
					255-color.getAlpha()
					).getRGB();
		}
	}
	private void distributeLight(float xOffset,float yOffset){
		lightAtPoint = new float[width*height];
		for(int i=0;i<width*height;i++){
			for(Light l : lights){
				int ty = i/width;
				int tx = i - ty*width; 
				tx *= SCALE;
				ty *= SCALE;
				int lx = (int)(l.getX()-xOffset);
				int ly = (int)(l.getY()-yOffset);
				if( l.getRadius()*l.getRadius() - (ty-ly)*(ty-ly) - (tx-lx)*(tx-lx) >= 1e-6){
					lightAtPoint[i] += l.getPower()*(l.getRadius()-Math.sqrt((ty-ly)*(ty-ly) + (tx-lx)*(tx-lx)))/l.getRadius();
				}
				lightAtPoint[i] = Utils.clampF(lightAtPoint[i], 0, 1);
			}
		}
		for(int i=0;i<width*height;i++){
			pixels[i] = new Color(
					255,
					255,
					255,
					lightAtPoint[i]
					).getRGB();
		}
	}
	public void render(Graphics2D g2,float xOffset,float yOffset){
		
		g = (Graphics2D) lightMap.getGraphics();
		g.clearRect(0,0, width, height);
		g.setComposite(AlphaComposite.DstOut);
		for(Light l : lights){
			g.drawImage(l.getImage(), (int)(l.getX()-l.getRadius()-xOffset/SCALE), (int)(l.getY()-l.getRadius()-yOffset/SCALE), null);
		}
		g.dispose();
		lightMap.flush();
		
		g2.drawImage(lightMap,0,0,handler.getGameWidth(),handler.getGameHeight(),null);
	}
}
