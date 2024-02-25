# Motor Tester

Not a very complicated Motor Tester thing for WPILib.
All it really does is allow you to control motors via Shuffleboard / SmartDashboard using the `.set()` method of them.
`.set()` goes to PID and tells it to run at X% of its top speed from my understanding, so you don't get any properly good speed control, but it works.

Made for FIRST Robotics Competition Team #86, Team Resistance for the 2024 Season, Crescendo to assist in robot prototype testing.

## Operation
The only elements you need to worry about in operating the motor tester is the "Motor Toggle" elements and "Motor Power" elements.
- Motor Toggle
  - Turns the motor on and off separately from Motor Power
  - Listed as "Motor X Toggle" where X is 1, 2, 3, or 4
- Motor Power
  - The power fed into the `.set()` method
  - Accepts a number from -1 to +1, and anywhere in between
  - Negative numbers run the motor backwards
  - 0 is the same thing as turning the motor off
  - Numbers outside of the valid range should be filtered out, but I wouldn't bet on it
- Motor Invert
  - Inverts the motor direction when set to `true`
  - Code wise, it simply multiplies the power by -1 when it's `true`

You may see a "Toggler1", "Toggler2", etc., you can go ahead and ignore these, those are available for debug purposes
