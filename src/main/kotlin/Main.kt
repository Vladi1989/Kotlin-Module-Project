import java.util.*

val archiveList = mutableListOf<Archive>()

fun main(args: Array<String>) {
    while (true) {
        println("Список архивов")
        println("0. Создать архив")

        for (i in 1..archiveList.size) {
            println("$i. ${archiveList[i - 1].archiveName}")
        }
        println("${archiveList.size + 1}. Выход")

        val userChoice = readLine().toString()

        when (userChoice) {
            "0" -> {
                var archiveName: String
                do {
                    println("Введите название архива")
                    archiveName = readLine().toString()
                } while (archiveName.isBlank())
                archiveList.add(Archive(archiveName, mutableListOf()))
                println("Архив $archiveName успешно создан")
            }
            "${archiveList.size + 1}" -> break
            else -> {
                try {
                    val userChoiceInt = userChoice.toInt()
                    if (userChoiceInt > archiveList.size + 1) {
                        println("Ввод некорректный, введите еще раз!")
                    } else {
                        openArchive(userChoiceInt)
                    }
                } catch (e: NumberFormatException) {
                    println("Ввод некорректный, введите еще раз!")
                }
            }
        }
    }
}

fun openArchive(archiveNumber: Int) {
    val currentArchive = archiveList[archiveNumber - 1]
    while (true) {
        println("Список заметок архива ${currentArchive.archiveName}")
        println("0. Создать заметку")
        for (i in 1..currentArchive.notesList.size) {
            println("$i. ${currentArchive.notesList[i - 1].noteName}")
        }
        println("${currentArchive.notesList.size + 1}. Выход")

        val userChoice = readLine().toString()

        when (userChoice) {
            "0" -> createNote(currentArchive.notesList)
            "${currentArchive.notesList.size + 1}" -> break
            else -> {
                try {
                    val userChoiceInt = userChoice.toInt()
                    if (userChoiceInt > currentArchive.notesList.size + 1) {
                        println("Ввод некорректный, введите еще раз!")
                    } else {
                        openNote(currentArchive.notesList[userChoiceInt - 1])
                    }
                } catch (e: NumberFormatException) {
                    println("Ввод некорректный, введите еще раз!")
                }
            }
        }
    }
}

fun createNote(notesList: MutableList<Note>) {
    var noteName: String
    do {
        println("Введите название заметки")
        noteName = readLine().toString()
    } while (noteName.isBlank())

    var noteContent: String
    do {
        println("Введите содержимое заметки")
        noteContent = readLine().toString()
    } while (noteContent.isBlank())

    notesList.add(Note(noteName, noteContent))
}

fun openNote(currentNote: Note) {
    println("Название заметки: ${currentNote.noteName}")
    println("Содержимое заметки: \n${currentNote.noteContent}")
}