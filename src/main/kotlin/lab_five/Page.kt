package lab_five

import java.util.*


class Page {
    private val MaxPage_num = 100

    private val PageSequence = IntArray(MaxPage_num)

    private val ProcessBlocks = Array(MaxPage_num) {
        IntArray(
            MaxPage_num
        )
    }

    private val PageCount = IntArray(MaxPage_num)

    private var PageNum = 0

    private var MissingPageNum = 0

    private var MissingPageRate = 0.0
    private val found = false

    private var BlockNum = 0

    private var j = 0
    private var i = 0
    private var k = 0

    private val NULL = -1

    fun original() {
        i = 0
        while (i < PageNum) {
            j = 0
            while (j < BlockNum) {
                ProcessBlocks[i][j] = NULL
                j++
            }
            i++
        }
        MissingPageNum = 1
    }

    fun input() {
        val sc = Scanner(System.`in`)
        println("分别输入物理块数量 页面数 页面序列")
        BlockNum = sc.nextInt()
        PageNum = sc.nextInt()
        i = 0
        while (i < PageNum) {
            PageSequence[i] = sc.nextInt()
            i++
        }
    }

    /**
     * 先进先出算法 FIFO
     */
    fun FIFO() {
        original()
        ProcessBlocks[0][0] = PageSequence[0]
        var temp = 0
        var flag = 0
        i = 0
        while (i < PageNum) {
            j = 0
            while (j < BlockNum) {
                if (PageSequence[i] === ProcessBlocks[flag][j]) {
                    break
                }
                j++
            }
            if (j === BlockNum) {
                k = 0
                while (k < BlockNum) {
                    if (ProcessBlocks[flag][k] === NULL) {
                        break
                    } else {
                        ProcessBlocks[i][k] = ProcessBlocks[flag][k]
                    }
                    k++
                }
                temp++
                temp = temp % BlockNum
                ProcessBlocks[i][temp] = PageSequence[i]
                MissingPageNum++
                flag = i
            } else {
                i++
                continue
            }
            i++
        }
    }


    /**
     * LRU最近最久未使用算法
     */
    fun LRU() {
        original()
        ProcessBlocks[0][0] = PageSequence[0]
        var temp: Int
        var flag = 0
        PageCount[0] = 0
        i = 0
        while (i < PageNum) {
            j = 0
            while (j < BlockNum) {
                if (PageSequence[i] === ProcessBlocks[flag][j]) {
                    PageCount[j] = i
                    break
                }
                j++
            }
            if (j !== BlockNum) {
                i++
                continue
            }
            k = 0
            while (k < BlockNum) {
                if (ProcessBlocks[flag][k] === NULL) {
                    break
                } else {
                    ProcessBlocks[i][k] = ProcessBlocks[flag][k]
                }
                k++
            }
            j = 0
            while (j < BlockNum) {
                if (ProcessBlocks[i][j] === NULL) {
                    ProcessBlocks[i][j] = PageSequence[i]
                    PageCount[j] = i
                    MissingPageNum++
                    flag = i
                    break
                }
                j++
            }
            if (j !== BlockNum) {
                i++
                continue
            }
            temp = 0
            j = 0
            while (j < BlockNum) {
                if (PageCount[temp] > PageCount[j]) {
                    temp = j
                }
                j++
            }
            ProcessBlocks[i][temp] = PageSequence[i]
            PageCount[temp] = i
            MissingPageNum++
            flag = i
            i++
        }
    }

    /**
     * 输出结果
     */
    fun Output() {
        MissingPageRate = MissingPageNum.toDouble() / PageNum
        i = 0
        while (i < PageNum) {
            print("====")
            i++
        }
        println(" ")
        i = 0
        while (i < PageNum) {
            print("${PageSequence[i]}")
            print(" ")
            i++
        }
        println(" ")
        i = 0
        while (i < PageNum) {
            print("====")
            i++
        }
        println(" ")
        j = 0
        while (j < BlockNum) {
            i = 0
            while (i < PageNum) {
                if (ProcessBlocks[i][j] === NULL) {
                    print("  ")
                } else {
                    print("${ProcessBlocks[i][j]}")
                    print(" ")
                }
                i++
            }
            println(" ")
            j++
        }
        println("缺页次数=$MissingPageNum\n缺页率=${MissingPageRate * 100} %")
    }

    fun Chance() {
        input()
        val sc = Scanner(System.`in`)
        println("请输入想要选择的算法  1:LRU 2:FIFO 0:退出")
        val choice = sc.nextInt()
        when (choice) {
            1 -> {
                println("最近最久未使用算法LRU")
                LRU()
                Output()
            }

            2 -> {
                println("先进先出 FIFO")
                FIFO()
                Output()
            }

            0 -> {}
            else -> {
                println("你的输入不对 重新输入")
                Chance()
            }
        }
    }

}