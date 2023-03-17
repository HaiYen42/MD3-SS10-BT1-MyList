import java.util.Arrays;

public class MyList<E> {
    private static final int DEFAULT_CAPACITY=10;
    private int size;
    private Object[] elements;

    // Constructor tạo ra mảng có kích thước mặc định =10;
    public MyList(){
        this.elements= new Object[DEFAULT_CAPACITY];
    }
    // Constructor tạo ra mảng có kích thước định trước
    public MyList(int capacity){
        this.elements= new Object[capacity];
    }

    // Thêm phần tử vào vị trí index trong mảng
    void add(int index, E element){
        if (index <0||index>=size) {
            throw new ArrayIndexOutOfBoundsException(); //Thông báo lỗi
        }else{
            ensureCapacity();// hàm kiểm tra kích thước lý thuyết và kích thước thực tế của mảng
            Object temp = elements[index];
            elements[index] = element;
            for (int i = index+1; i < size+1; i++) {
                elements[i]= temp;
                temp= elements[i+1];
            }
            size++;
        }
    }

    // Xóa phần tử khỏi mảng
    E remove (int index){
        if (index <0||index>=size) {
            throw new ArrayIndexOutOfBoundsException("Index: "+ index+ " Actual size: "+ size);// thông báo lỗi
        }else{
            Object deleteElements= elements[index];
            for (int i = index; i <size ; i++) {
                //dùng để dịch phần tử sau index về index-1
                elements[i]= elements[i+1];

            }
            size--;
            return (E) deleteElements;
        }
    }

    // Steps 05: Trả ra kích thước của mảng
    int size(){
        return this.size;
    }
    // Steps 6: Sao chép mảng hiện tại
    @Override
    public E clone(){
        Object cloneArray = Arrays.copyOf(this.elements, size);
        return (E) cloneArray;
    }
    //Step 7: Kiểm tra sự tồn tại phần tử của mảng true/false
    boolean contains(E o){
        // Dựa theo kết quả của hàm indexOf để trả về true/false (có/không)
        return indexOf(o)>0;
    }
    // Step 8: Tìm vị trí phần tử trong mảng
    int indexOf(E o){
        if (o == null) {
            for (int i = 0; i <size ; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        }else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elements[i])) {
                    return i;
                }
            }
        }
        return -1;
    }
    //Nối phần tử chỉ định vào cuối mảng
    boolean add(E o){
        ensureCapacity();
        elements[size++]=o;
        return true;
    }
    // Mở rộng mảng dữ liệu
    private void ensureCapacity() {
        if (size == elements.length) {
            int newSize = elements.length*2;
            this.elements= (Object[]) Arrays.copyOf(elements, newSize);

        }
    }

    //Trả ra phần tử
    E get(int index){
        if (index <0||index>=size) {
            throw new ArrayIndexOutOfBoundsException("Index: "+ index+ "Actural size "+ size);
        } else{
            return (E) elements[index];
        }
    }
    // Xóa tất cả các phần tử khỏi mảng = size =0
    void clear(){
        this.size=0;
    }

}
