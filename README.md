# Sigil Placer
A simple tool to place sigils on the board which is made for the game [Sigils of Elohim](https://www.croteam.com/TalosApp/).

## Example

### Input
```java
public class Main {
    public static void main(String[] args) {
        Placer placer = new Placer.Builder(5, 8)
                .numOfI(1)
                .numOfO(1)
                .numOfLL(4)
                .numOfLR(1)
                .numOfZL(1)
                .numOfT(2)
                .build();
        placer.run();
    }
}
```

### Output
("╳" for empty space)
```
Solution found:
┏┳━━┳┓
┃┗━┓┃┃
┣━┳╋┛┃
┃┏┛┣━┫
┃┃┏┫╳┃
┣╋┛┗┳┫
┃┗┳━┛┃
┃┏┻━━┫
┗┻━━━┛
```
