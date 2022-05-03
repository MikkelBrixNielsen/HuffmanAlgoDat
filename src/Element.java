//Mikkel Brix Nielsen (mikke21)
//Nicolai Larsen (dalar21)
//Steffen Bach (stbac21)

public class Element {

    private int key;
    private HuffmanTree data;

    public Element(int i, HuffmanTree o){
	this.key = i;
	this.data = o;
    }
    public int getKey(){
	return this.key;
    }
    public HuffmanTree getData(){
	return this.data;
    }
}
