import cv2
import easyocr
from ultralytics import YOLO
import sys
import os
import re

# Get current folder
BASE_DIR = os.path.dirname(os.path.abspath(__file__))

# Load YOLO model
model = YOLO(os.path.join(BASE_DIR, "models", "best.pt"))

# Load EasyOCR
reader = easyocr.Reader(['en'])

# Open webcam
cap = cv2.VideoCapture(0)

while True:
    ret, frame = cap.read()

    if not ret:
        break

    results = model(frame)

    for result in results:
        boxes = result.boxes

        for box in boxes:
            x1, y1, x2, y2 = map(int, box.xyxy[0])

            # Draw bounding box
            cv2.rectangle(frame, (x1, y1), (x2, y2), (0, 255, 0), 2)

            # Crop detected plate
            plate = frame[y1:y2, x1:x2]

            # Read text
            text = reader.readtext(plate, detail=0)

            if text:

                # Join OCR result
                plate_number = "".join(text)

                # Remove spaces and convert to uppercase
                plate_number = plate_number.replace(" ", "").upper()

                # Indian number plate format:
                # TN38AB1234
                pattern = r"^[A-Z]{2}[0-9]{2}[A-Z]{1,2}[0-9]{4}$"

                # Accept only valid plates
                if re.match(pattern, plate_number):

                    cv2.putText(
                        frame,
                        plate_number,
                        (x1, y1 - 10),
                        cv2.FONT_HERSHEY_SIMPLEX,
                        0.8,
                        (0, 255, 0),
                        2
                    )

                    print(plate_number)
                    sys.stdout.flush()

                    cap.release()
                    cv2.destroyAllWindows()
                    sys.exit(0)

    cv2.imshow("ANPR", frame)

    if cv2.waitKey(1) & 0xFF == ord('q'):
        break

cap.release()
cv2.destroyAllWindows()