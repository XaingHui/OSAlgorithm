package lab_two

import business.Calculate
import business.Work
import java.util.*
import kotlin.collections.ArrayList


class PriorityScheduling(
    override var pname: String,
    override var atime: Int,
    override var stime: Int,
) : Work {

    override var ftime: Int = 1
    override var rtime: Int = 1
    override var drtime: Double = 1.0
    override var aver_rtime: Double = 1.0
    override var aver_drtime: Double = 1.0
    override var priority: Int = 1

    constructor() : this("", 0, 0)

    companion object {
        fun PS(works: ArrayList<Work>, N: Int = 2): ArrayList<Work> {
            println("--------------------优先级调度-------------------")
            for (i in 0 until N)
                works.add(PriorityScheduling())
            works.forEach { it.ReadWork() }
            val PS_temp = works.sortedBy { it.atime }
            var PS_return = ArrayList<Work>()
            PS_temp.forEach { PS_return.add(it) }
            PS_return = Calculate.calculate(PS_return)
            return PS_return
        }
    }


    override fun ReadWork(): PriorityScheduling {
        val read = Scanner(System.`in`)
        println("请输入")
        println("作业名\t到达时间\t服务时间\t优先级\t并以回车结束")
        pname = read.next()
        atime = read.nextInt()
        stime = read.nextInt()
        priority = read.nextInt()
        return PriorityScheduling(pname, atime, stime)
    }

    override fun PrintWork() {
        print("${pname}\t\t")
        print("${atime}\t\t")
        print("${stime}\t\t")
        print("${priority}\t\t")
        print("${ftime}\t\t")
        print("${rtime}\t\t")
        print("${drtime}\t\t")
        println()
    }
}