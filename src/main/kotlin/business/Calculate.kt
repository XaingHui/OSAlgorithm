package business
import lab_one.FirstComeFirstServer
import lab_one.ShortJobFirst
import lab_two.PriorityScheduling

interface Calculate {
    companion object {
        fun calculate(works: ArrayList<Work>): ArrayList<Work> {
            var sum1 = 0.00
            var sum2 = 0.00
            works[0].ftime = works[0].atime + works[0].stime
            var time = works[0].ftime
            val temp_Queue = ArrayList<Work>()
            val tempList = ArrayList<Work>(works.size)
            tempList.add(works[0])

            for (i in 1 until works.size) {
                if (works[i].atime <= time) {
                    temp_Queue.add(works[i])
                }
            }
            if (temp_Queue.last() is FirstComeFirstServer)
                temp_Queue.sortBy { it.atime }
            else if (temp_Queue.last() is ShortJobFirst)
                temp_Queue.sortBy { it.stime }
            else if (temp_Queue.last() is PriorityScheduling)
                temp_Queue.sortByDescending { it.priority }

            for (j in 0 until works.size) {
                temp_Queue.forEach { tempList.add(it) }
                temp_Queue.clear()
                for (i in 0 until tempList.size) {
                    if (i == 0)
                        tempList[0].ftime = tempList[0].atime + tempList[0].stime
                    else if (tempList[i].atime > tempList[i - 1].ftime)
                        tempList[i].ftime = tempList[i].atime + tempList[i].stime;
                    else
                        tempList[i].ftime = tempList[i - 1].ftime + tempList[i].stime;
                }
                time = tempList.last().ftime
            }

            tempList.run {
                forEach {
                    it.rtime = it.ftime - it.atime
                    it.drtime = (it.rtime / it.stime).toDouble()
                    sum1 += it.rtime
                    it.aver_rtime = (sum1 / works.size)
                    sum2 += it.drtime
                    it.aver_drtime = sum2 / works.size
                }
            }
            return tempList
        }
    }
}