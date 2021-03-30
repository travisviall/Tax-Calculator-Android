# Tax-Calculator-Android

An Android tax calculator app that uses multiple Fragments and a single Activity to calculate a tax amount using a SeekBar and TextView fields.  The Java class BigDecimal is also implemented to handle decimal and currency conversion.

Fragments extend the Fragment interface to utilize the overridden methods that handle the Fragment life-cycle.  The method onPause() is used to access the SharedPreferences class methods to save the user's input when the app is rotated or closed and re-opened.
