    private fun backupOutputFile(fileOut: File, tiedostonimi: String) {

        //get file as bytes

        val fileBytes = if (fileOut.exists()) fileOut.readBytes() else ByteArray(0)

        lifecycleScope.launch {

            withContext(Dispatchers.IO) {
                // Luo backup-kansio, jos semmosta ei ole jo olemassa.
                // Polku on storage/emulated/0/Android/data/fi.finn_d.attunelogi/files
                val backupDir = File(context?.getExternalFilesDir(null), "backup")
                if (!backupDir.exists()) {
                    backupDir.mkdirs()
                }

                // Listaa tiedostot kansiossa ja jos niitä on yli tietyn määrän, poista vanhin
                // Muutetaan ajalliseks eli x päivän kuluttua vanhin poistetaan?
                val backupFiles = backupDir.listFiles()
                val maxBackupFiles = 20
                if (backupFiles != null && backupFiles.size >= maxBackupFiles) {
                    val sortedFiles = backupFiles.sortedBy { it.lastModified() }
                    for (i in 0 until (backupFiles.size - maxBackupFiles + 1)) {
                        sortedFiles[i].delete()
                    }
                }

                //if (fileOut.exists()) {
                if (fileBytes.isNotEmpty()) {
                    val backupFile = File(backupDir, "Backup_" + tiedostonimi)
                    //val inputStream = FileInputStream(fileOut)

                    val outputStream = FileOutputStream(backupFile)

                    outputStream.write(fileBytes)

                    outputStream.close()

                    withContext(Dispatchers.Main) {
                        _binding.textView4.append("Tiedosto varmuuskopioitu.\n")
                    }
                }
            }
        }
    }
