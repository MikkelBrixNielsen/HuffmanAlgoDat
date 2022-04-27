public class HuffmanTree {
    private int bitValue;
    private HuffmanTree left = null;
    private HuffmanTree right = null;

    public HuffmanTree(int bitValue){
        this.bitValue = bitValue;
    }

    public HuffmanTree(HuffmanTree left, HuffmanTree right){
        this.bitValue = -1;
        this.right = right;
        this.left = left;
    }

    public int getBitValue() {
        return bitValue;
    }

    public HuffmanTree left() {
        return left;
    }

    public HuffmanTree right(){
        return right;
    }

    public void setBitValue(int bitValue) {
        this.bitValue = bitValue;
    }

    public void setLeft(HuffmanTree left) {
        this.left = left;
    }

    public void setRight(HuffmanTree right) {
        this.right = right;
    }


}
