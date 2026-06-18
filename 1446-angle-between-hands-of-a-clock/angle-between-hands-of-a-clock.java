class Solution {
    public double angleClock(int hour, int minutes) {
        // Hour hand angle
        double hourAngle = (hour % 12) * 30 + minutes * 0.5;
        
        // Minute hand angle
        double minuteAngle = minutes * 6;
        
        // Difference between the two angles
        double angle = Math.abs(hourAngle - minuteAngle);
        
        // Return the smaller angle
        return Math.min(angle, 360 - angle);
    }
}