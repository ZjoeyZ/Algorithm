"""
1,选出枢轴pivot
2，两个指针从左到右边，
3，指针重合一次快排结束，
3，将结果递归
"""
import random

list1 = [1, 3, 5, 6, 2, 4]
Range = 100
Length = 6
list = random.sample(range(Range), Length)


def partition(list, low, high):
    pivot = list[low]
    while (low < high):
        while list[high] >= pivot:
            #debug: 遍历完了也没找到符合条件的值
            high = high - 1
            if high == low:
                break
        list[low] = list[high]
        while list[low] <= pivot:
            low = low + 1
            if low == high:
                break
        list[high] = list[low]
    list[high] = pivot
    return list, high


def quik(list, low, high):
    if low < high:
        list, pivot = partition(list, low, high)
        list = quik(list, low, pivot - 1)
        list = quik(list, pivot + 1, high)
        return list
    return list


print(quik(list1, 0, len(list1) - 1))
print(quik(list, 0, len(list) - 1))
