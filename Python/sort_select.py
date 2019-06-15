"""
每次遍历找出最小值，然后和最前的值交换位置
1, 遍历n-1次,,每次遍历找出最小值,最开始设遍历的第一个数为最小值
2，每次最小值要和之后的所有值比较一次，并不断更新最小值
3，比较结束后，最小值同遍历中的第一个值交换位置
"""

list1 = [1, 3, 5, 6, 2, 4]


def slect_sort(list):
    n = len(list)
    # 遍历n-1次
    for t in range(0, n - 1):
        start_index = t
        min_index = start_index
        # 比较n-t次
        for s in range(start_index + 1, n):
            if list[min_index] > list[s]:
                min_index = s
        if min_index != start_index:
            print("最小值不在最前面，需要交换位置")
            list[min_index], list[start_index] = list[start_index], list[min_index]
        print("遍历第{}次, 此次的最小值时{}".format(t, list[start_index]), list)
    return list


def slect_sort2(list):
    for i in range(0, len(list) - 1):
        min = i
        for j in range(i + 1, len(list)):
            if list[min] > list[j]:
                min = j
        list[min], list[i] = list[i], list[min]
    return list


# print(slect_sort(list1))
# print(slect_sort2(list1))


def select_sort3(list):
    for i in range(0, len(list) - 1):
        min = i
        for j in range(i + 1, len(list)):
            if list[min] > list[j]:
                min = j
        list[min], list[i] = list[i], list[min]
print(slect_sort2(list1))
