public class HoffmanTree {
/*    int frequency;*/
    int data;
    HoffmanTree left = null;
    HoffmanTree right = null;

    public HoffmanTree(int data){
        this.data = data;
    }

    public HoffmanTree(){
        this.data = -1;
    }


/*    public int frequency() {
        return frequency;
    }*/

    public int getData() {
        return data;
    }

    public HoffmanTree left() {
        return left;
    }

    public HoffmanTree right(){
        return right;
    }
}
