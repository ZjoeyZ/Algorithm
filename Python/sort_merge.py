"""
归并排序：
1,对一个list进行归并排序
2，把它分出两份进行归并排序
3，把归并排序好了的两个list，merge归并在一起
"""
import random

def merge(left, right):
    i, j = 0, 0
    temp = []
    print('this is a merge', right, left)
    while i < len(left) and j < len(right):
        if left[i] < right[j]:
            temp.append(left[i])
            i = i + 1
        else:
            temp.append(right[j])
            j = j + 1
    temp += left[i:]
    temp += right[j:]
    print("merge over", temp)
    return  temp


def merge_sort(list):
    if len(list) <= 1:
        return list
    mid = int(len(list) / 2)
    left = merge_sort(list[:mid])
    right = merge_sort(list[mid:])
    list = merge(left, right)
    return list


list1 = [1, 3, 5, 6, 2, 4]
Range = 100
Length = 6
list2 = random.sample(range(Range), Length)

print(merge_sort(list2))
