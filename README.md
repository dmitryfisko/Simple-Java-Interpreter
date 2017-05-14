Example of supported code:
```
int n = 400;
for (int i = 2; i <= n; i++) {
    int temp = i;
    for (int j = 2; j < i; j++) {
        while (temp % j == 0) {
            temp = temp / j;
        }
    }
    if (temp != 1) {
        System.out.println(i);
    } else {
        temp = i;
        System.out.print(i);
        System.out.print();
        for (int j = 2; j <= i; j++) {
            while (temp % j == 0) {
                temp = temp / j;
                System.out.print(j);
            }
        }
        System.out.println();
    }
}
```