package lab_one

import business.Calculate
import business.Work
import sun.nio.cs.ext.SJIS
import java.util.*
import kotlin.collections.ArrayList

class ShortJobFirst(override var pname: String, override var atime: Int, override var stime: Int) : Work, Calculate {
    override var ftime: Int = 1
    override var rtime: Int = 1
    override var drtime: Double = 1.0
    override var aver_rtime: Double = 1.0
    override var aver_drtime: Double = 1.0
    override var priority: Int = 1

    constructor() : this("", 0, 0)

    companion object {
        fun SJF(works: ArrayList<Work>, N: Int = 2): ArrayList<Work> {
            println("--------------------短作业优先-----------------")
            for (i in 0 until N)
                works.add(ShortJobFirst())
            works.forEach { it.ReadWork() }
            val SJF_temp = works.sortedBy { it.atime }
            var SJF_return = ArrayList<Work>()
            SJF_temp.forEach { SJF_return.add(it) }
            SJF_return = Calculate.calculate(SJF_return)
            return SJF_return
        }

    }
}