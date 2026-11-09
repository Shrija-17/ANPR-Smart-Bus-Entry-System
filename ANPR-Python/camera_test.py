import cv2

# Open default webcam
camera = cv2.VideoCapture(0)

if not camera.isOpened():
    print("Error: Camera not found!")
    exit()

while True:
    ret, frame = camera.read()

    if not ret:
        print("Failed to capture frame")
        break

    cv2.imshow("Smart Bus Gate - Live Camera", frame)

    # Press Q to exit
    if cv2.waitKey(1) & 0xFF == ord('q'):
        break

camera.release()
cv2.destroyAllWindows()