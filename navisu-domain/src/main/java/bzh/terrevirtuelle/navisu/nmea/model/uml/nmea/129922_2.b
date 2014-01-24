class AISType5
!!!152578.java!!!	decodeFrame() : void
        if (messageAisBinary.length() == 425) {
            MMSI = binaryToInt(messageAisBinary, 8, 38);
            IMO = binaryToInt(messageAisBinary, 40, 70);
            CallSign = binaryToString(messageAisBinary, 70, 112);
            name = binaryToString(messageAisBinary, 112, 232);
            shipType = binaryToInt(messageAisBinary, 232, 240);
            destination = binaryToString(messageAisBinary, 302, 422);
            draught = (float) (0.1 * binaryToInt(messageAisBinary, 294, 302));
            electronicPositionDevice = binaryToInt(messageAisBinary, 270, 274);
            length = binaryToInt(messageAisBinary, 240, 249) + binaryToInt(messageAisBinary, 249, 258);
            width = binaryToInt(messageAisBinary, 258, 264) + binaryToInt(messageAisBinary, 264, 270);
            month = binaryToInt(messageAisBinary, 274, 278);
            day = binaryToInt(messageAisBinary, 278, 283);
            hour = binaryToInt(messageAisBinary, 283, 288);
            minute = binaryToInt(messageAisBinary, 288, 294);
            ETA = new GregorianCalendar(year, month, day, hour, minute);
        }
!!!152706.java!!!	ConcatAisBinary(in ligne : String) : void
        String messageBinaire = "";
        for (int i = 0; i < messageAis.length(); i++) {
            char c = messageAis.charAt(i);
            int cInt = (int) c + 40;
            if (cInt > 128) {
                cInt = cInt + 32;
            } else {
                cInt = cInt + 40;
            }
            String charBinaire = Integer.toBinaryString(cInt);
            charBinaire = charBinaire.substring(charBinaire.length() - 6, charBinaire.length());
            messageBinaire = messageBinaire.concat(charBinaire);
        }
        /* remove padding bits at the end the message */
        messageBinaire = messageBinaire.substring(0, messageBinaire.length() - this.padding - 1);
        messageAisBinary = messageBinaire;
!!!152834.java!!!	toString() : String
        DateFormat dateFormat = new SimpleDateFormat("hh:mm dd-MM");
        StringBuilder sb = new StringBuilder();
        String s = "AISType5{MMSI=" + MMSI
                + ", NAME=" + name
                + ", TYPE=" + shipType
                + ", LENGTH=" + length
                + ", WIDTH=" + width
                + ", DRAUGHT=" + draught;
        sb.append(s);
        if (ETA != null) {
            s = ", ETA=" + dateFormat.format(ETA.getTime());
        } else {
            s = ", ETA= ";
        }
        sb.append(s);
        s = ", DEST=" + destination + "}";
        sb.append(s);
        return sb.toString();
