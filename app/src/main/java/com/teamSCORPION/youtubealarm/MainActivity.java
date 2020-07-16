package com.teamSCORPION.youtubealarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView sticker, divider1, divider2, divider3;
    Bitmap morning, afternoon, evening, night;
    Bitmap morning_line, afternoon_line, evening_line, night_line;
    Bitmap morning_first, afternoon_first, evening_first, night_first;
    Drawable[] btn_options_id, btn_add_id;
    ConstraintLayout main_layout;
    Button btn_options, btn_add;
    TextView Message, Date_id, Time_id;
    String[] quotes;
    int random_no;
    String Name, Date, Time;
    IntentFilter s_intentFilter;
    Switch sw_one, sw_two, sw_three;
    TextView name, title, description, keyword1, keyword2, keyword3, keyword4, keyword5;
    TextView fav_alarm1, fav_alarm2, fav_alarm3;
    Button ok, skip;
    EditText name_edit, keyword1_edit, keyword2_edit, keyword3_edit, keyword4_edit, keyword5_edit;
    TextView alarm_status;
    int fav_hr_1, fav_hr_2, fav_hr_3;
    int fav_min_1, fav_min_2, fav_min_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        sticker = findViewById(R.id.sticker);
        main_layout = findViewById(R.id.main_layout);
        btn_options = findViewById(R.id.btn_options);
        btn_add = findViewById(R.id.button_add);
        Message = findViewById(R.id.textView_message);
        divider1 = findViewById(R.id.divider1);
        divider2 = findViewById(R.id.divider2);
        divider3 = findViewById(R.id.divider3);
        Date_id = findViewById(R.id.textView_date);
        Time_id = findViewById(R.id.textView_time);
        sw_one = findViewById(R.id.switch1);
        sw_two = findViewById(R.id.switch2);
        sw_three = findViewById(R.id.switch3);
        fav_alarm1 = findViewById(R.id.fav_alarm1);
        fav_alarm2 = findViewById(R.id.fav_alarm2);
        fav_alarm3 = findViewById(R.id.fav_alarm3);
        title = findViewById(R.id.fg_title);
        description = findViewById(R.id.fg_description);
        keyword1 = findViewById(R.id.fg_kw1);
        keyword2 = findViewById(R.id.fg_kw2);
        keyword3 = findViewById(R.id.fg_kw3);
        keyword4 = findViewById(R.id.fg_kw4);
        keyword5 = findViewById(R.id.fg_kw5);
        ok = findViewById(R.id.fg_ok);
        skip = findViewById(R.id.fg_skip);
        keyword1_edit = findViewById(R.id.fg_kw1_edit);
        keyword2_edit = findViewById(R.id.fg_kw2_edit);
        keyword3_edit = findViewById(R.id.fg_kw3_edit);
        keyword4_edit = findViewById(R.id.fg_kw4_edit);
        keyword5_edit = findViewById(R.id.fg_kw5_edit);
        alarm_status = findViewById(R.id.alarm_status);
        name = findViewById(R.id.fg_name);
        name_edit = findViewById(R.id.fg_name_edit);

        final SharedPreferences clockSettings = getSharedPreferences("MyClockPreferences", 0);

        s_intentFilter = new IntentFilter();
        s_intentFilter.addAction(Intent.ACTION_TIME_TICK);
        s_intentFilter.addAction(Intent.ACTION_TIMEZONE_CHANGED);
        s_intentFilter.addAction(Intent.ACTION_TIME_CHANGED);

        final Calendar[] rightNow = {Calendar.getInstance()};
        final int currentHourIn24Format = rightNow[0].get(Calendar.HOUR_OF_DAY);
        final int[] currentHourIn12Format = {rightNow[0].get(Calendar.HOUR)};
        final int[] currentMinute = {rightNow[0].get(Calendar.MINUTE)};
        final int[] currentDay = {rightNow[0].get(Calendar.DAY_OF_WEEK)};
        final int[] currentDate = {rightNow[0].get(Calendar.DAY_OF_MONTH)};
        final int[] currentMonth = {rightNow[0].get(Calendar.MONTH)};
        final int[] currentYear = {rightNow[0].get(Calendar.YEAR)};
        rightNow[0] = Calendar.getInstance();
        currentHourIn12Format[0] = rightNow[0].get(Calendar.HOUR_OF_DAY);
        currentMinute[0] = rightNow[0].get(Calendar.MINUTE);
        Date = find_date(Arrays.toString(currentDate).replaceAll("\\[|\\]|,|\\s", "")) + "/" + find_month(Arrays.toString(currentMonth).replaceAll("\\[|\\]|,|\\s", "")) + "/" + Arrays.toString(currentYear).replaceAll("\\[|\\]|,|\\s", "") + ", " + find_day(Arrays.toString(currentDay).replaceAll("\\[|\\]|,|\\s", ""));
        if(currentHourIn12Format[0] < 10 && currentMinute[0] > 9){
            Time = "0" + currentHourIn12Format[0] + " : " + currentMinute[0];
        }else if(currentHourIn12Format[0] > 9 && currentMinute[0] < 10){
            Time = currentHourIn12Format[0] + " : " + "0" + currentMinute[0];
        }else{
            Time = currentHourIn12Format[0] + " : " + currentMinute[0];
        }

        Random r = new Random();
        random_no = r.nextInt(164);

        btn_options_id = new Drawable[4];
        btn_options_id[0] = ResourcesCompat.getDrawable(getResources(), R.drawable.m_settings_btn, null);
        btn_options_id[1] = ResourcesCompat.getDrawable(getResources(), R.drawable.a_settings_btn, null);
        btn_options_id[2] = ResourcesCompat.getDrawable(getResources(), R.drawable.e_settings_btn, null);
        btn_options_id[3] = ResourcesCompat.getDrawable(getResources(), R.drawable.n_settings_btn, null);

        btn_add_id = new Drawable[4];
        btn_add_id[0] = ResourcesCompat.getDrawable(getResources(), R.drawable.m_add_btn, null);
        btn_add_id[1] = ResourcesCompat.getDrawable(getResources(), R.drawable.a_add_btn, null);
        btn_add_id[2] = ResourcesCompat.getDrawable(getResources(), R.drawable.e_add_btn, null);
        btn_add_id[3] = ResourcesCompat.getDrawable(getResources(), R.drawable.n_add_btn, null);

        quotes = new String[164];
        quotes[0] = "Learn to value yourself, which means: fight for your happiness.";
        quotes[1] = "The true secret of happiness lies in the taking a genuine interest in all the details of daily life.";
        quotes[2] = "The greatest happiness you can have is knowing that you do not necessarily require happiness.";
        quotes[3] = "People should find happiness in the little things, like family.";
        quotes[4] = "As people spin faster and faster in the pursuit of merely personal happiness, they become exhausted in the futile effort of chasing themselves.";
        quotes[5] = "All happiness or unhappiness solely depends upon the quality of the object to which we are attached by love.";
        quotes[6] = "Happiness consists more in conveniences of pleasure that occur everyday than in great pieces of good fortune that happen but seldom.";
        quotes[7] = "Happiness is not an ideal of reason, but of imagination.";
        quotes[8] = "To be without some of the things you want is an indispensable part of happiness.";
        quotes[9] = "There is some kind of a sweet innocence in being human—in not having to be just happy or just sad—in the nature of being able to be both broken and whole, at the same time.";
        quotes[10] = "Even a happy life cannot be without a measure of darkness, and the word happy would lose its meaning if it were not balanced by sadness. It is far better to take things as they come along with patience and equanimity.";
        quotes[11] = "There is no happiness like that of being loved by your fellow creatures, and feeling that your presence is an addition to their comfort.";
        quotes[12] = "If you want happiness for an hour, take a nap. If you want happiness for a day, go fishing. If you want happiness for a year, inherit a fortune. If you want happiness for a lifetime, help someone else.";
        quotes[13] = "The only way to find true happiness is to risk being completely cut open";
        quotes[14] = "Nobody really cares if you’re miserable, so you might as well be happy.";
        quotes[15] = "It isn’t what you have or who you are or where you are or what you are doing that makes you happy or unhappy. It is what you think about it.";
        quotes[16] = "People are unhappy when they get something too easily. You have to sweat – that’s the only moral they know.";
        quotes[17] = "We can’t control the world. We can only (barely) control our own reactions to it. Happiness is largely a choice, not a right or entitlement.";
        quotes[18] = "Happiness cannot be traveled to, owned, earned, worn or consumed. Happiness is the spiritual experience of living every minute with love, grace, and gratitude.";
        quotes[19] = "Happiness is being content with what you have, living in freedom and liberty, having a good family life and good friends.";
        quotes[20] = "Happiness is the interval between periods of unhappiness.";
        quotes[21] = "The world is full of people looking for spectacular happiness while they snub contentment.";
        quotes[22] = "Happiness grows at our own firesides, and is not to be picked in strangers’ gardens.";
        quotes[23] = "Happiness is excitement that has found a settling down place. But there is always a little corner that keeps flapping around.";
        quotes[24] = "If only we’d stop trying to be happy we could have a pretty good time.";
        quotes[25] = "The essence of philosophy is that a man should so live that his happiness shall depend as little as possible on external things.";
        quotes[26] = "The search for happiness is one of the chief sources of unhappiness.";
        quotes[27] = "My family didn’t have a lot of money, and I’m grateful for that. Money is the longest route to happiness.";
        quotes[28] = "Happiness lies in the joy of achievement and the thrill of creative effort.";
        quotes[29] = "We tend to forget that happiness doesn’t come as a result of getting something we don’t have, but rather of recognizing and appreciating what we do have.";
        quotes[30] = "There can be no happiness if the things we believe in are different from the things we do.";
        quotes[31] = "Happiness is a direction, not a place.";
        quotes[32] = "Happiness depends upon ourselves.";
        quotes[33] = "A great obstacle to happiness is to expect too much happiness.";
        quotes[34] = "It is not how much we have, but how much we enjoy, that makes happiness.";
        quotes[35] = "Happiness is not something ready made. It comes from your own actions.";
        quotes[36] = "I think the key to life is just being a happy person, and happiness will bring you success.";
        quotes[37] = "Happiness is always the serendipitous result of looking for something else.";
        quotes[38] = "Happiness is not a goal; it is a by-product.";
        quotes[39] = "Happiness is a place between too much and too little.";
        quotes[40] = "Give a man health and a course to steer, and he’ll never stop to trouble about whether he’s happy or not.";
        quotes[41] = "Happiness is having a large, loving, caring, close-knit family in another city.";
        quotes[42] = "There is only one happiness in this life, to love and be loved.";
        quotes[43] = "How simple it is to see that we can only be happy now, and there will never be a time when it is not now.";
        quotes[44] = "No medicine cures what happiness cannot.";
        quotes[45] = "Cheerfulness is what greases the axles of the world. Don’t go through life creaking.";
        quotes[46] = "Happiness and sadness run parallel to each other. When one takes a rest, the other one tends to take up the slack.";
        quotes[47] = "True happiness is not attained through self-gratification, but through fidelity to a worthy purpose.";
        quotes[48] = "Happiness is a form of courage.";
        quotes[49] = "All happiness depends on courage and work.";
        quotes[50] = "Now and then it’s good to pause in our pursuit of happiness and just be happy.";
        quotes[51] = "So we shall let the reader answer this question for himself: who is the happier man, he who has braved the storm of life and lived or he who has stayed securely on shore and merely existed?";
        quotes[52] = "Happiness is a matter of one’s most ordinary and everyday mode of consciousness being busy and lively and unconcerned with self.";
        quotes[53] = "Happiness is distraction from the human tragedy.";
        quotes[54] = "Real happiness is not of temporary enjoyment, but is so interwoven with the future that it blesses for ever.";
        quotes[55] = "Real happiness is cheap enough, yet how dearly we pay for its counterfeit.";
        quotes[56] = "The foolish man seeks happiness in the distance, the wise grows it under his feet.";
        quotes[57] = "I must learn to be content with being happier than I deserve.";
        quotes[58] = "Who is the happiest of men? He who values the merits of others, and in their pleasure takes joy, even as though it were his own.";
        quotes[59] = "Ask yourself whether you are happy and you cease to be so.";
        quotes[60] = "Happiness is where we find it, but very rarely where we seek it.";
        quotes[61] = "You cannot protect yourself from sadness without protecting yourself from happiness.";
        quotes[62] = "Three grand essentials to happiness in this life are something to do, something to love, and something to hope for.";
        quotes[63] = "The happiness which is lacking makes one think even the happiness one has unbearable.";
        quotes[64] = "It’s been my experience that you can nearly always enjoy things if you make up your mind firmly that you will.";
        quotes[65] = "Happiness is only real when shared.";
        quotes[66] = "If you look to others for fulfillment, you will never be fulfilled. If your happiness depends on money, you will never be happy with yourself. Be content with what you have; rejoice in the way things are. When you realize there is nothing lacking, the world belongs to you.";
        quotes[67] = "You can’t be happy unless you’re unhappy sometimes.";
        quotes[68] = "In order to have great happiness you have to have great pain and unhappiness – otherwise how would you know when you’re happy?";
        quotes[69] = "There are two things to aim at in life: first, to get what you want; and after that, to enjoy it. Only the wisest of mankind achieve the second.";
        quotes[70] = "There is no cosmetic for beauty like happiness.";
        quotes[71] = "Let us be grateful to people who make us happy, they are the charming gardeners who make our souls blossom.";
        quotes[72] = "He who lives in harmony with himself lives in harmony with the universe.";
        quotes[73] = "The happiness of your life depends upon the quality of your thoughts.";
        quotes[74] = "Happiness is not a state to arrive at, but a manner of traveling.";
        quotes[75] = "Happiness is a constant work-in-progress, because solving problems is a constant work-in-progress – the solutions to today’s problems will lay the foundation for tomorrow’s problems, and so on. True happiness occurs only when you find the problems you enjoy having and enjoy solving.";
        quotes[76] = "Happiness is when what you think, what you say, and what you do are in harmony.";
        quotes[77] = "In my life I’ve learned that true happiness comes from giving. Helping others along the way makes you evaluate who you are. I think that love is what we’re all searching for. I haven’t come across anyone who didn’t become a better person through love.";
        quotes[78] = "Happiness is a well-balanced combination of love, labour, and luck.";
        quotes[79] = "Happiness always looks small while you hold it in your hands, but let it go, and you learn at once how big and precious it is.";
        quotes[80] = "Ups and downs. Victories and defeats. Sadness and happiness. That’s the best kind of life.";
        quotes[81] = "Sanity and happiness are an impossible combination.";
        quotes[82] = "Many things can make you miserable for weeks; few can bring you a whole day of happiness.";
        quotes[83] = "Happiness in this world, when it comes, comes incidentally. Make it the object of pursuit, and it leads us a wild-goose chase, and is never attained.";
        quotes[84] = "Happiness is the default state. It’s what’s there when you remove the sense that something is missing in life.";
        quotes[85] = "Those who can laugh without cause have either found the true meaning of happiness or have gone stark raving mad.";
        quotes[86] = "Happiness is holding someone in your arms and knowing you hold the whole world.";
        quotes[87] = "Happiness is the resultant of the relative strengths of positive and negative feelings rather than an absolute amount of one or the other.";
        quotes[88] = "Happiness is an accident of nature, a beautiful and flawless aberration.";
        quotes[89] = "Happiness is the natural flower of duty.";
        quotes[90] = "It’s the moments that I stopped just to be, rather than do, that have given me true happiness.";
        quotes[91] = "Happiness is a conscious choice, not an automatic response.";
        quotes[92] = "Most people would rather be certain they’re miserable, than risk being happy.";
        quotes[93] = "To be happy, you must fancy that everything you have is a gift, and you the chosen, though you worked your tail off for every bit of it.";
        quotes[94] = "Love is that condition in which the happiness of another person is essential to your own.";
        quotes[95] = "Don’t waste your time in anger, regrets, worries, and grudges. Life is too short to be unhappy.";
        quotes[96] = "The happiness of life is made up of the little charities of a kiss or smile, a kind look, a heartfelt compliment.";
        quotes[97] = "Sometimes life knocks you on your ass… get up, get up, get up!!! Happiness is not the absence of problems, it’s the ability to deal with them.";
        quotes[98] = "Happiness is not being pained in body or troubled in mind.";
        quotes[99] = "Happiness makes up in height for what it lacks in length.";
        quotes[100] = "The secret of happiness is to find a congenial monotony.";
        quotes[101] = "The greatest happiness of life is the conviction that we are loved; loved for ourselves, or rather, loved in spite of ourselves.";
        quotes[102] = "Happiness is a function of accepting what is.";
        quotes[103] = "Happiness is the meaning and the purpose of life, the whole aim and end of human existence.";
        quotes[104] = "Happiness is not a goal; it is a by-product.";
        quotes[105] = "Happiness is a state of activity.";
        quotes[106] = "Happiness lies in the joy of achievement and the thrill of creative effort.";
        quotes[107] = "Plenty of people miss their share of happiness, not because they never found it, but because they didn’t stop to enjoy it.";
        quotes[108] = "Happiness is an inside job.";
        quotes[109] = "If you want to be happy, be";
        quotes[110] = "If you are not happy here and now, you never will be.";
        quotes[111] = "We cannot be happy if we expect to live all the time at the highest peak of intensity. Happiness is not a matter of intensity but of balance and order and rhythm and harmony.";
        quotes[112] = "We all live with the objective of being happy; our lives are all different and yet the same.";
        quotes[113] = "I have only two kinds of days: happy and hysterically happy.";
        quotes[114] = "You will never be happy if you continue to search for what happiness consists of. You will never live if you are looking for the meaning of life.";
        quotes[115] = "Whoever is happy will make others happy.";
        quotes[116] = "You don’t develop courage by being happy in your relationships everyday. You develop it by surviving difficult times and challenging adversity.";
        quotes[117] = "Many persons have a wrong idea of what constitutes true happiness. It is not attained through self-gratification but through fidelity to a worthy purpose.";
        quotes[118] = "Most people would rather be certain they’re miserable, than risk being happy.";
        quotes[119] = "The fact is always obvious much too late, but the most singular difference between happiness and joy is that happiness is a solid and joy a liquid.";
        quotes[120] = "Happy girls are the prettiest.";
        quotes[121] = "Folks are usually about as happy as they make their minds up to be.";
        quotes[122] = "On the whole, the happiest people seem to be those who have no particular cause for being happy except that they are so.";
        quotes[123] = "There are two ways of being happy: We must either diminish our wants or augment our means – either may do – the result is the same and it is for each man to decide for himself and to do that which happens to be easier.";
        quotes[124] = "Be happy with what you have. Be excited about what you want.";
        quotes[125] = "Success is not the key to happiness. Happiness is the key to success. If you love what you are doing, you will be successful.";
        quotes[126] = "Judge nothing, you will be happy. Forgive everything, you will be happier. Love everything, you will be happiest.";
        quotes[127] = "No one is in control of your happiness but you; therefore, you have the power to change anything about yourself or your life that you want to change.";
        quotes[128] = "Success is getting what you want, happiness is wanting what you get.";
        quotes[129] = "Be happy. It’s one way of being wise.";
        quotes[130] = "Man is fond of counting his troubles, but he does not count his joys. If he counted them up as he ought to, he would see that every lot has enough happiness provided for it.";
        quotes[131] = "Too often in life, something happens and we blame other people for us not being happy or satisfied or fulfilled. So the point is, we all have choices, and we make the choice to accept people or situations or to not accept situations.";
        quotes[132] = "Indeed, man wishes to be happy even when he so lives as to make happiness impossible.";
        quotes[133] = "The Constitution only guarantees the American people the right to pursue happiness. You have to catch it yourself.";
        quotes[134] = "The best way to cheer yourself up is to try to cheer somebody else up.";
        quotes[135] = "Can anything be so elegant as to have few wants, and to serve them one’s self?";
        quotes[136] = "The habit of being happy enables one to be freed, or largely freed, from the domination of outward conditions.";
        quotes[137] = "You can only have bliss if you don’t chase it.";
        quotes[138] = "If only we’d stop trying to be happy we could have a pretty good time.";
        quotes[139] = "Be happy for this moment. This moment is your life.";
        quotes[140] = "Happiness is like a butterfly which, when pursued, is always beyond our grasp, but, if you will sit down quietly, may alight upon you.";
        quotes[141] = "If you want to be happy, set a goal that commands your thoughts, liberates your energy, and inspires your hopes.";
        quotes[142] = "If you want others to be happy, practice compassion. If you want to be happy, practice compassion.";
        quotes[143] = "Just as a cautious businessman avoids investing all his capital in one concern, so wisdom would probably admonish us also not to anticipate all our happiness from one quarter alone.";
        quotes[144] = "A truly happy person is one who can enjoy the scenery while on a detour.";
        quotes[145] = "The only way to avoid being miserable is not to have enough leisure to wonder whether you are happy or not.";
        quotes[146] = "If there were in the world today any large number of people who desired their own happiness more than they desired the unhappiness of others, we could have a paradise in a few years.";
        quotes[147] = "Joy is a net of love by which you can catch souls.";
        quotes[148] = "When neither their property nor their honor is touched, the majority of men live content.";
        quotes[149] = "We have no more right to consume happiness without producing it than to consume wealth without producing it.";
        quotes[150] = "We are no longer happy so soon as we wish to be happier.";
        quotes[151] = "Unquestionably, it is possible to do without happiness; it is done involuntarily by nineteen-twentieths of mankind.";
        quotes[152] = "Happiness consists more in conveniences of pleasure that occur everyday than in great pieces of good fortune that happen but seldom.";
        quotes[153] = "For every minute you are angry you lose sixty seconds of happiness.";
        quotes[154] = "Happiness is when what you think, what you say, and what you do are in harmony.";
        quotes[155] = "Happiness is your dentist telling you it won’t hurt and then having him catch his hand in the drill.";
        quotes[156] = "Even a happy life cannot be without a measure of darkness, and the word happy would lose its meaning if it were not balanced by sadness. It is far better to take things as they come along with patience and equanimity.";
        quotes[157] = "It isn’t what you have or who you are or where you are or what you are doing that makes you happy or unhappy. It is what you think about it.";
        quotes[158] = "One joy scatters a hundred griefs.";
        quotes[159] = "Happiness is not something you postpone for the future; it is something you design for the present.";
        quotes[160] = "If you want happiness for an hour – take a nap. If you want happiness for a day – go fishing. If you want happiness for a month – get married. If you want happiness for a year – inherit a fortune. If you want happiness for a lifetime – help someone else.";
        quotes[161] = "The foolish man seeks happiness in the distance, the wise grows it under his feet.";
        quotes[162] = "Most of us believe in trying to make other people happy only if they can be happy in ways which we approve.";
        quotes[163] = "Learn to let go. That is the key to happiness.";

        morning_line = BitmapFactory.decodeResource(getResources(),R.drawable.m_line);
        afternoon_line = BitmapFactory.decodeResource(getResources(),R.drawable.a_line);
        evening_line = BitmapFactory.decodeResource(getResources(),R.drawable.e_line);
        night_line = BitmapFactory.decodeResource(getResources(),R.drawable.n_line);

        morning = BitmapFactory.decodeResource(getResources(),R.drawable.m_sticker);
        afternoon = BitmapFactory.decodeResource(getResources(),R.drawable.a_sticker);
        evening = BitmapFactory.decodeResource(getResources(),R.drawable.e_sticker2);
        night = BitmapFactory.decodeResource(getResources(),R.drawable.n_sticker);

        morning_first = BitmapFactory.decodeResource(getResources(),R.drawable.m_glass);
        afternoon_first = BitmapFactory.decodeResource(getResources(),R.drawable.a_glass);
        evening_first = BitmapFactory.decodeResource(getResources(),R.drawable.e_glass);
        night_first = BitmapFactory.decodeResource(getResources(),R.drawable.n_glass);

        Date_id.setText(Date);
        Time_id.setText(Time);

        if(clockSettings.getBoolean("my_first_time", true)){
            SharedPreferences.Editor prefEditor = clockSettings.edit();
            prefEditor.putInt("FavHr1", 5);
            prefEditor.putInt("FavMin1", 30);
            prefEditor.putInt("FavHr2", 6);
            prefEditor.putInt("FavMin2", 30);
            prefEditor.putInt("FavHr3", 7);
            prefEditor.putInt("FavMin3", 30);
            prefEditor.putBoolean("my_first_time", false);
            prefEditor.apply();
            enable_foreground(currentHourIn24Format);
        }else{
            fav_hr_1 = clockSettings.getInt("FavHr1", 5);
            fav_hr_2 = clockSettings.getInt("FavHr2", 6);
            fav_hr_3 = clockSettings.getInt("FavHr3", 7);;
            fav_min_1 = clockSettings.getInt("FavMin1", 30);
            fav_min_2 = clockSettings.getInt("FavMin2", 30);
            fav_min_3 = clockSettings.getInt("FavMin3", 30);
            if(fav_hr_1 > 9 & fav_min_1 > 9){
                fav_alarm1.setText(fav_hr_1 + " : " + fav_min_1);
            }else if(fav_hr_1 < 10 & fav_min_1 < 10){
                fav_alarm1.setText("0" + fav_hr_1 + " : " + "0" +fav_min_1);
            }else if(fav_hr_1 > 9 & fav_min_1 < 10){
                fav_alarm1.setText(fav_hr_1 + " : " + "0" +fav_min_1);
            }else{
                fav_alarm1.setText("0" + fav_hr_1 + " : " + fav_min_1);
            }

            if(fav_hr_2 > 9 & fav_min_2 > 9){
                fav_alarm1.setText(fav_hr_2 + " : " + fav_min_2);
            }else if(fav_hr_2 < 10 & fav_min_2 < 10){
                fav_alarm1.setText("0" + fav_hr_2 + " : " + "0" +fav_min_2);
            }else if(fav_hr_2 > 9 & fav_min_2 < 10){
                fav_alarm1.setText(fav_hr_2 + " : " + "0" +fav_min_2);
            }else{
                fav_alarm1.setText("0" + fav_hr_2 + " : " + fav_min_2);
            }

            if(fav_hr_3 > 9 & fav_min_3 > 9){
                fav_alarm1.setText(fav_hr_3 + " : " + fav_min_3);
            }else if(fav_hr_3 < 10 & fav_min_3 < 10){
                fav_alarm1.setText("0" + fav_hr_3 + " : " + "0" +fav_min_3);
            }else if(fav_hr_3 > 9 & fav_min_3 < 10){
                fav_alarm1.setText(fav_hr_3 + " : " + "0" +fav_min_3);
            }else{
                fav_alarm1.setText("0" + fav_hr_3 + " : " + fav_min_3);
            }

            enable_background(currentHourIn24Format, clockSettings);
        }

        final BroadcastReceiver m_timeChangedReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                final String action = intent.getAction();
                assert action != null;
                if (action.equals(Intent.ACTION_TIME_TICK)) {
                    rightNow[0] = Calendar.getInstance();
                    currentHourIn12Format[0] = rightNow[0].get(Calendar.HOUR_OF_DAY);
                    currentMinute[0] = rightNow[0].get(Calendar.MINUTE);
                    String Time_new;
                    if(currentHourIn12Format[0] < 10 && currentMinute[0] > 9){
                        Time_new = "0" + currentHourIn12Format[0] + " : " + currentMinute[0];
                    }else if(currentHourIn12Format[0] > 9 && currentMinute[0] < 10){
                        Time_new = currentHourIn12Format[0] + " : " + "0" + currentMinute[0];
                    }else{
                        Time_new = currentHourIn12Format[0] + " : " + currentMinute[0];
                    }
                    Time_id.setText(Time_new);
                }
            }
        };

        registerReceiver(m_timeChangedReceiver, s_intentFilter);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ip_name = name_edit.getText().toString();
                String ip_kw1 = keyword1_edit.getText().toString();
                String ip_kw2 = keyword2_edit.getText().toString();
                String ip_kw3 = keyword3_edit.getText().toString();
                String ip_kw4 = keyword4_edit.getText().toString();
                String ip_kw5 = keyword5_edit.getText().toString();
                SharedPreferences.Editor prefEditor = clockSettings.edit();
                prefEditor.putString("UserName", ip_name);
                prefEditor.putString("KeyWord1", ip_kw1);
                prefEditor.putString("KeyWord2", ip_kw2);
                prefEditor.putString("KeyWord3", ip_kw3);
                prefEditor.putString("KeyWord4", ip_kw4);
                prefEditor.putString("KeyWord5", ip_kw5);
                prefEditor.putBoolean("my_first_time", false);
                prefEditor.apply();
                enable_background(currentHourIn24Format, clockSettings);
            }
        });

        btn_options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, settings.class);
                startActivity(intent);
                finish();
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor prefEditor = clockSettings.edit();
                prefEditor.putBoolean("my_first_time", false);
                prefEditor.apply();
                enable_background(currentHourIn24Format, clockSettings);
            }
        });
    }

    String find_day(String number){
        String output;
        switch (number){
            case "1": output = "Sunday";
                break;
            case "2": output = "Monday";
                break;
            case "3": output = "Tuesday";
                break;
            case "4": output = "Wednesday";
                break;
            case "5": output = "Thursday";
                break;
            case "6": output = "Friday";
                break;
            case "7": output = "Saturday";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + number);
        }
        return output;
    }

    String find_month(String number){
        String output;
        switch (number){
            case "0": output = "01";
                break;
            case "1": output = "02";
                break;
            case "2": output = "03";
                break;
            case "3": output = "04";
                break;
            case "4": output = "05";
                break;
            case "5": output = "06";
                break;
            case "6": output = "07";
                break;
            case "7": output = "08";
                break;
            case "8": output = "09";
                break;
            case "9": output = "10";
                break;
            case "10": output = "11";
                break;
            case "11": output = "12";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + number);
        }
        return output;
    }

    String find_date(String number){
        String output;
        switch (number){
            case "1": output = "01";
                break;
            case "2": output = "02";
                break;
            case "3": output = "03";
                break;
            case "4": output = "04";
                break;
            case "5": output = "05";
                break;
            case "6": output = "06";
                break;
            case "7": output = "07";
                break;
            case "8": output = "08";
                break;
            case "9": output = "09";
                break;
            default: output = number;
        }
        return output;
    }

    void enable_foreground(int currentHourIn24Format){
        btn_options.setVisibility(View.GONE);
        btn_add.setVisibility(View.GONE);
        sticker.setVisibility(View.GONE);
        Message.setVisibility(View.GONE);
        divider1.setVisibility(View.GONE);
        divider2.setVisibility(View.GONE);
        divider3.setVisibility(View.GONE);
        Date_id.setVisibility(View.GONE);
        Time_id.setVisibility(View.GONE);
        sw_one.setVisibility(View.GONE);
        sw_two.setVisibility(View.GONE);
        sw_three.setVisibility(View.GONE);
        fav_alarm1.setVisibility(View.GONE);
        fav_alarm2.setVisibility(View.GONE);
        fav_alarm3.setVisibility(View.GONE);
        alarm_status.setVisibility(View.GONE);
        title.setVisibility(View.VISIBLE);
        description.setVisibility(View.VISIBLE);
        name.setVisibility(View.VISIBLE);
        name_edit.setVisibility(View.VISIBLE);
        keyword1.setVisibility(View.VISIBLE);
        keyword2.setVisibility(View.VISIBLE);
        keyword3.setVisibility(View.VISIBLE);
        keyword4.setVisibility(View.VISIBLE);
        keyword5.setVisibility(View.VISIBLE);
        ok.setVisibility(View.VISIBLE);
        skip.setVisibility(View.VISIBLE);
        keyword1_edit.setVisibility(View.VISIBLE);
        keyword2_edit.setVisibility(View.VISIBLE);
        keyword3_edit.setVisibility(View.VISIBLE);
        keyword4_edit.setVisibility(View.VISIBLE);
        keyword5_edit.setVisibility(View.VISIBLE);
        if(currentHourIn24Format > 3 & currentHourIn24Format < 12){
            main_layout.setBackgroundResource(R.drawable.m_glass);
        }else if(currentHourIn24Format > 11 & currentHourIn24Format < 17){
            main_layout.setBackgroundResource(R.drawable.a_glass);
        }else if(currentHourIn24Format > 16 & currentHourIn24Format < 21){
            main_layout.setBackgroundResource(R.drawable.e_glass);
        }else {
            main_layout.setBackgroundResource(R.drawable.n_glass);
        }
    }

    void enable_background(int currentHourIn24Format, SharedPreferences clockSettings){
        Name = clockSettings.getString("UserName", "");

        title.setVisibility(View.GONE);
        description.setVisibility(View.GONE);
        name.setVisibility(View.GONE);
        name_edit.setVisibility(View.GONE);
        keyword1.setVisibility(View.GONE);
        keyword2.setVisibility(View.GONE);
        keyword3.setVisibility(View.GONE);
        keyword4.setVisibility(View.GONE);
        keyword5.setVisibility(View.GONE);
        ok.setVisibility(View.GONE);
        skip.setVisibility(View.GONE);
        keyword1_edit.setVisibility(View.GONE);
        keyword2_edit.setVisibility(View.GONE);
        keyword3_edit.setVisibility(View.GONE);
        keyword4_edit.setVisibility(View.GONE);
        keyword5_edit.setVisibility(View.GONE);
        btn_options.setVisibility(View.VISIBLE);
        btn_add.setVisibility(View.VISIBLE);
        sticker.setVisibility(View.VISIBLE);
        Message.setVisibility(View.VISIBLE);
        divider1.setVisibility(View.VISIBLE);
        divider2.setVisibility(View.VISIBLE);
        divider3.setVisibility(View.VISIBLE);
        Date_id.setVisibility(View.VISIBLE);
        Time_id.setVisibility(View.VISIBLE);
        sw_one.setVisibility(View.VISIBLE);
        sw_two.setVisibility(View.VISIBLE);
        sw_three.setVisibility(View.VISIBLE);
        fav_alarm1.setVisibility(View.VISIBLE);
        fav_alarm2.setVisibility(View.VISIBLE);
        fav_alarm3.setVisibility(View.VISIBLE);
        alarm_status.setVisibility(View.VISIBLE);
        if(currentHourIn24Format > 3 & currentHourIn24Format < 12){
            main_layout.setBackgroundColor(Color.parseColor("#f3989d"));
            sticker.setImageBitmap(morning);
            btn_options.setBackground(btn_options_id[0]);
            btn_add.setBackground(btn_add_id[0]);
            Message.setTextColor(Color.parseColor("#f2e3e4"));
            divider1.setImageBitmap(morning_line);
            divider2.setImageBitmap(morning_line);
            divider3.setImageBitmap(morning_line);
            Message.setText("Hi"+Name+", Good Morning\n\n"+quotes[random_no]);
        }else if(currentHourIn24Format > 11 & currentHourIn24Format < 17){
            main_layout.setBackgroundColor(Color.parseColor("#d63447"));
            sticker.setImageBitmap(afternoon);
            btn_options.setBackground(btn_options_id[1]);
            btn_add.setBackground(btn_add_id[1]);
            Message.setTextColor(Color.parseColor("#f9c3c3"));
            divider1.setImageBitmap(afternoon_line);
            divider2.setImageBitmap(afternoon_line);
            divider3.setImageBitmap(afternoon_line);
            Message.setText("Hi"+Name+", Good Afternoon\n\n"+quotes[random_no]);
        }else if(currentHourIn24Format > 16 & currentHourIn24Format < 21){
            main_layout.setBackgroundColor(Color.parseColor("#febc6e"));
            sticker.setImageBitmap(evening);
            btn_options.setBackground(btn_options_id[2]);
            btn_add.setBackground(btn_add_id[2]);
            Message.setTextColor(Color.parseColor("#ffffff"));
            divider1.setImageBitmap(evening_line);
            divider2.setImageBitmap(evening_line);
            divider3.setImageBitmap(evening_line);
            Message.setText("Hi"+Name+", Good Evening\n\n"+quotes[random_no]);
        }else {
            main_layout.setBackgroundColor(Color.parseColor("#202020"));
            sticker.setImageBitmap(night);
            btn_options.setBackground(btn_options_id[3]);
            btn_add.setBackground(btn_add_id[3]);
            Message.setTextColor(Color.parseColor("#807e7e"));
            divider1.setImageBitmap(night_line);
            divider2.setImageBitmap(night_line);
            divider3.setImageBitmap(night_line);
            Message.setText("Hi"+Name+", Good Night\n\n"+quotes[random_no]);
        }
    }
}