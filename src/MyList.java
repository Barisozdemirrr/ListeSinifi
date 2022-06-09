import java.util.Arrays;

public class MyList <T> {
    private int capacity = 10;

    private T[] array;

    //MyList() : Boş contructor kullanılırsa dizinin başlangıç boyutu 10 olmalıdır.
    public MyList() {
        this.array = (T[]) new Object[capacity];
    }

    //MyList(int capacity) : Dizinin başlangıç değeri capacity parametresinden alınmalıdır.
    public MyList(int capacity) {
        this.array = (T[]) new Object[this.capacity];
    }

    //size() : dizideki eleman sayısını verir.
    public int size() {
        int size = 0;
        for (int i = 0; i < getCapacity(); i++) {
            if (array[i] != (null)) {
                size++;
            }
        }
        return size;

    }
    //getCapacity() : dizinin kapasite değerini verir.
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
        array = (T[]) new Object[this.capacity];
    }

    //add(T data) : sınıfa ait diziye eleman eklemek için kullanılmalıdır.
    // Eğer dizide yeteri kadar yer yok ise, dizi boyutu 2 katına çıkartılmalıdır.
    public void add(T data) {
        if (size() >= getCapacity()) {
            T[] tempArray = this.array;
            setCapacity(getCapacity() * 2);
            this.array = (T[]) new Object[getCapacity()];
            this.array = Arrays.copyOf(tempArray, getCapacity());
        }
        this.array[size()] = data;
    }

    //get(int index): verilen indisteki değeri döndürür. Geçersiz indis girilerse null döndürür.
    public T get(int index) {
        if (isInvalidIndex(index)) {
            return null;
        }
        return this.array[index];
    }

    private boolean isInvalidIndex(int index) {
        return size() < index;
    }


//remove(int index): verilen indisteki veriyi silmeli ve silinen indis sonrasında ki verileri sol doğru kaydırmalı.
// Geçersiz indis girilerse null döndürür.

    public T remove(int index) {
        if (isInvalidIndex(index)) {
            return null;
        }
        T[] temp = this.array;
        this.array[index] = null;
        for (int i = index; i < size(); i++) {
            if (size() - i == 1) this.array[i] = null;
            else this.array[i] = temp[i + 1];
        }
        return this.array[index];

    }
//set(int index, T data) : verilen indisteki veriyi yenisi ile değiştirme işlemini yapmalıdır.
// Geçersiz indis girilerse null döndürür.
    public T set(int index, T data) {
        if (isInvalidIndex(index)) {
            return null;
        }
        this.array[index] = data;
        System.out.println("Veri yenisi ile degistirildi !");
        return this.array[index];
    }

//String toString() : Sınıfa ait dizideki elemanları listeleyen bir metot.
public String toString() {
    if(isEmpty()) return "[]";

    StringBuilder sb = new StringBuilder();
    sb.append("[");

    for(T value : this.array) {
        if(isNull(value)) break;
        sb.append(value).append(", ");
    }

    // After the loop, sb will be something like this -->  "[10, 20, 30, ", we need to replace the ", " with "]"
    sb.replace(sb.length() - 2, sb.length() - 1, "]");

    return sb.toString();

    // We could also use this.array.toString() but I want to create my own func.
}

   //int indexOf(T data) : Parametrede verilen nesnenin listedeki indeksini verir.
   // Nesne listede yoksa -1 değerini verir.
    public int indexOf(T object) {
        for (int i = 0; i < size(); i++) {
            if (this.array[i] == object) {
                return i;
            }
        }
        return -1;
    }

    //int lastIndexOf(T data) : Belirtilen öğenin listedeki son indeksini söyler.
    // Nesne listede yoksa -1 değerini verir.
    public int lastIndexOf(T object) {
        for (int i = size() - 1; i >= 0; i--) {
            if (this.array[i] == object) {
                return i;
            }
        }
        return -1;
    }

    //boolean isEmpty() : Listenin boş olup olmadığını söyler.
    public boolean isEmpty(){
        return size()==0;
    }

    //T[] toArray() : Listedeki öğeleri, aynı sırayla bir array haline getirir.
    public T[] toArray(){
        return this.array;
    }

    //clear() : Listedeki bütün öğeleri siler, boş liste haline getirir.
    public void clear(){
        this.array=(T[]) new Object[getCapacity()];
    }

    //MyList<T> sublist(int start,int finish) : Parametrede verilen indeks aralığına ait bir liste döner.
    public MyList<T> subList(int start, int finish) {
        MyList<T> myList = new MyList<>();
        while (start <= finish) {
           myList.add(this.array[start]);
            start++;
        }

        return myList;
    }

   //boolean contains(T data) : Parametrede verilen değerin dizide olup olmadığını söyler.
    public boolean contains(T object){
        for(T obj:this.array){
            if(obj.equals(object)){
                return true;
            }
        }
        return false;
    }
    public boolean isNull(T value) {
        return value == null;
    }
}
