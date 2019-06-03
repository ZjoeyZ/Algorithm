# 遍历n -1次
# 每次遍历比较次数递减
list1 = [1, 3, 5, 6, 2, 4]


def bubble(list):
    for i in range(0, len(list) - 1):
        for j in range(0, len(list) - i - 1):
            if list[j] > list[j + 1]:
                list[j], list[j + 1] = list[j + 1], list[j]
    return list


print(bubble(list1))
