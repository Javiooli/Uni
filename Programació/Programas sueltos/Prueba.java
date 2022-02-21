
public class Prueba {
    public void main()
    {
        Character [] array1, array2;
        Character [] aminoacids = {'C','G','A','T'};
        
        for (int i = 0; i < 10; i++){
            array1 = new Character [i];
            array2 = new Character [i];
            for (int j = 0; j < i; i++){
                array1[j] = aminoacids[(int) (Math.random() * (3 - 1 + 1) + 1)];
            }
            for (int desp = 0; desp < i; desp++){
                for (int j = 1; j < i; j++){
                    array2[j] = array1[j-1];
                }
                array2[0] = array1[array1.length-1];
            }

        }
        
    }
}
