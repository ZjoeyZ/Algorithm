"""
外部排序的步骤：
1，分段：将外存的大文件分出个m个段
2，排序：
    段进内存
    使用内部排序，
    再写入外存（这些有序的段叫做归并段，或顺串）
3，归并：（n路平衡归并：每次将n个或<n个段归并在一起）
        增加路数可以减少趟数，但也增加了每一趟的比较的次数
使用败者数归并的思路：
    避开外存存储细节，认为归并段已经在内存之中
    1，读入每个并归段的第一个关键字到装到外结点数组[]里,顺便再在里面加入一最小值，
    2，创建败者树，败者树list是没有叶子结点的完全二叉树，每个结点初始值为 最小值的下标
    3，开始调整list，从数组中区一关键字及其下标，得到他的父节点，让他和父节点的下标的指向值比较
        如果它小它胜利，如果它大他失败，则将他的下标同父节点存的下标交换
        父节点原来的指向值代替它继续向上面的父节点比较
        直到第0个父节点
        让每个结点存储 胜者（较大值）的下标
        调整m次（外结点个数）败者树创建完成
    4，如果败者树的顶点指向值不是我们规定的最大值，则
        输出顶点指向值，进入输入并归段
        在输入并归段中删除它
        取其后一个值加入外结点，
        调整败者树
"""
# 三路败者树排序
# 树中节点纪录比较失败者列的序号（起始为1）
# 列读取完毕将序号置为0
# 胜者为0则所有列已读取完

def loser_tree_sort(lists):
    #读入每个list当前第一个值进叶子
    leaves = []
    final_list = []
    for i in lists:
        leaves.append(i[0])
    loser_tree = creat_loser_tree(leaves)

    for i in lists:
        max_key = 1000
        i.append(max_key)

    while leaves[loser_tree[0]] != max_key:
        #哪一个list要更新呢？ index
        index = loser_tree[0]
        num = lists[index].pop(0)
        final_list.append(num)
        #更新leaves
        leaves[index] = lists[index][0]
        loser_tree= adjust(loser_tree, leaves, index)
        print(final_list)
    return final_list


def creat_loser_tree(leaves):
    loser_tree = []
    min_key = -1000
    leaves.append(min_key)
    for i in range(0, 3):
        #loser tree 的3 给值都是 -1000的index
        loser_tree.append(3)
    print("初始败者树", loser_tree)
    for i in range(0, 3):
        loser_tree = adjust(loser_tree, leaves, i)
    return loser_tree


def adjust(loser_tree, leaves, i):
    #loser_tree = [3, 3, 3]
    #leaves = [10, 5, 7, -1000]
    print("adjust for {}th time".format(i))
    parent_index = int((i + 3) / 2)
    while parent_index > 0:
        # print("leaves index i is {}".format(i))
        # print("parent_index is", parent_index)
        if leaves[i] > leaves[loser_tree[parent_index]]:
            #数字大的为败者，留下index
            loser_tree[parent_index], i = i, loser_tree[parent_index]
        parent_index = int(parent_index / 2)

    loser_tree[0] = i
    return  loser_tree


lists = [
    [4, 8, 10, 15],
    [5, 14, 21],
    [1, 63, 99],
]

print(loser_tree_sort(lists))
