package lab_one

import business.Calculate
import business.Work
import kotlin.collections.ArrayList

class FirstComeFirstServer(
    override var pname: String, override var atime: Int,
    override var stime: Int,
) : Work, Calculate {

    override var ftime: Int = 1
    override var rtime: Int = 1
    override var drtime: Double = 1.0
    override var aver_rtime: Double = 1.0
    override var aver_drtime: Double = 1.0
    override var priority: Int = 1

    constructor() : this("", 0, 0)

    companion object {
        fun FCFS(works: ArrayList<Work>, N: Int = 2): ArrayList<Work> {
            println("--------------------先来先服务-----------------")
            for (i in 0 until N)
                works.add(FirstComeFirstServer())

            works.forEach { it.ReadWork() }
            val FCFS_temp = works.sortedBy { it.atime }
            val FCFS_return = ArrayList<Work>()
            FCFS_temp.forEach { FCFS_return.add(it) }
            Calculate.calculate(FCFS_return)
            return FCFS_return
        }
    }

}