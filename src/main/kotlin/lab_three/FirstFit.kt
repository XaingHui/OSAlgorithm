package lab_three

import business.Memory
import java.util.*

open class FirstFit {
    private val read = Scanner(System.`in`)
    private val maxSize = 100
    private var Memorys = LinkedList<Memory>()

    init {
        Memorys.add(Memory())
        Memorys.first.msize = maxSize
        Memorys.last.run {
            address = 0
            id = 0
            msize = maxSize
            isLocated = false
        }
    }


    fun memory(this_id: Int, this_size: Int): Boolean {
        for (i in 0..Memorys.lastIndex) {
            if (Memorys[i].msize >= this_size && !Memorys[i].isLocated) {
                if (Memorys[i].msize == this_size) {
                    Memorys[i].id = this_id
                    Memorys[i].isLocated = true
                    return true
                } else if (Memorys[i].msize > this_size) {
                    val memory = Memory()
                    memory.run {
                        id = this_id
                        msize = this_size
                        isLocated = true
                        address = Memorys[i].address
                        Memorys[i].address += this_size
                        Memorys[i].msize -= this_size
                    }
                    Memorys.addLast(memory)
                    return true
                }
            }
        }

        return false
    }


    fun show() {
        Memorys.sortBy { it.address }
        Memorys.forEach {
            println("****************************************************************")
            println("分区号:\t" + "${it.id}")
            println("大小:\t${it.msize}")
            println("地址:\t${it.address}")
            if (!it.isLocated)
                println("状态:\t空闲")
            else
                println("状态:\t被占用")
        }
    }

    fun allocation() {
        Memorys.sortBy { it.address }
        println("分别输入\tid\t内存大小")
        val this_id = read.nextInt()
        val this_size = read.nextInt()

        if (this_size <= 0)
            println("输入大小有误，请重新输入")
        else if (memory(this_id, this_size)) {
            println("内存分配成功")
            show()
        }
    }

    fun recycle() {
        Memorys.sortBy { it.address }
        println("请输入想要回收的作业\tid")
        val recId = read.nextInt()

        for (i in 0 until Memorys.size) {
            if (Memorys[i].id == recId) {
                Memorys[i].id = 0
                Memorys[i].isLocated = false
                if (i > 0 && !Memorys[i - 1].isLocated) {
                    Memorys[i - 1].msize += Memorys[i].msize
                    Memorys.remove(Memorys[i])
                }
                if (!Memorys[i + 1].isLocated) {
                    Memorys[i].msize += Memorys[i + 1].msize
                    Memorys.remove(Memorys[i + 1])
                }
                break
            }
        }
        show()
    }

    fun tight() {
        Memorys.sortByDescending { it.id }
        for (i in 0 until Memorys.size) {
            if (Memorys[i].id == 0) {
                if (i > 0 && !Memorys[i - 1].isLocated) {
                    Memorys[i - 1].msize += Memorys[i].msize
                    Memorys.remove(Memorys[i])
                }
                if (!Memorys[i + 1].isLocated) {
                    Memorys[i].msize += Memorys[i + 1].msize
                    Memorys.remove(Memorys[i + 1])
                }
                break
            }
        }
    }

}