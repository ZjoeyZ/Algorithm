"""
⒈ 从第一个元素开始，该元素可以认为已经被排序
⒉ 取出下一个元素，在已经排序的元素序列中从后向前扫描
⒊ 如果该元素（已排序）大于新元素，将该元素移到下一位置
⒋ 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
⒌ 将新元素插入到下一位置中
⒍ 重复步骤2~5
"""
# debug rang(i, 0, -1) 改成 range(i, -1, -1)
# 不然只有第一遍历会让第一个和第二值比较
import random

list1 = [1, 3, 5, 6, 2, 4]
Range = 100
Length = 6
list = random.sample(range(Range),Length)


def insert(list):
    for i in range(0, len(list) -1):
        for j in range(i, -1, -1):
            if list[j + 1] < list[j]:
                list[j + 1], list[j] = list[j], list[j + 1]
    return list

