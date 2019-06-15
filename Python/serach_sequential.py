def sequential(num, list):
    for i in list:
        if num == i:
            print("the {} is the same".format(i + 1))
            return
    print("no one matches")
    return

list1 = [1, 2, 3, 4, 5]
sequential(2, list1)
