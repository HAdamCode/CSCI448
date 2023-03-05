package edu.mines.csci448.examples.samodelkin.data.database;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import edu.mines.csci448.examples.samodelkin.data.SamodelkinCharacter;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@SuppressWarnings({"unchecked", "deprecation"})
public final class SamodelkinDao_Impl implements SamodelkinDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<SamodelkinCharacter> __insertionAdapterOfSamodelkinCharacter;

  private final SamodelkinTypeConverters __samodelkinTypeConverters = new SamodelkinTypeConverters();

  private final EntityDeletionOrUpdateAdapter<SamodelkinCharacter> __deletionAdapterOfSamodelkinCharacter;

  public SamodelkinDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSamodelkinCharacter = new EntityInsertionAdapter<SamodelkinCharacter>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `character` (`name`,`race`,`profession`,`dexterity`,`wisdom`,`strength`,`intelligence`,`constitution`,`charisma`,`avatar`,`id`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SamodelkinCharacter value) {
        if (value.getName() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getName());
        }
        if (value.getRace() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getRace());
        }
        if (value.getProfession() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getProfession());
        }
        stmt.bindLong(4, value.getDexterity());
        stmt.bindLong(5, value.getWisdom());
        stmt.bindLong(6, value.getStrength());
        stmt.bindLong(7, value.getIntelligence());
        stmt.bindLong(8, value.getConstitution());
        stmt.bindLong(9, value.getCharisma());
        if (value.getAvatarAssetPath() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getAvatarAssetPath());
        }
        final String _tmp = __samodelkinTypeConverters.fromUUID(value.getId());
        if (_tmp == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, _tmp);
        }
      }
    };
    this.__deletionAdapterOfSamodelkinCharacter = new EntityDeletionOrUpdateAdapter<SamodelkinCharacter>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `character` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, SamodelkinCharacter value) {
        final String _tmp = __samodelkinTypeConverters.fromUUID(value.getId());
        if (_tmp == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, _tmp);
        }
      }
    };
  }

  @Override
  public void addCharacter(final SamodelkinCharacter samodelkinCharacter) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfSamodelkinCharacter.insert(samodelkinCharacter);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteCrime(final SamodelkinCharacter samodelkinCharacter) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfSamodelkinCharacter.handle(samodelkinCharacter);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public Flow<List<SamodelkinCharacter>> getCharacters() {
    final String _sql = "SELECT * FROM character";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"character"}, new Callable<List<SamodelkinCharacter>>() {
      @Override
      public List<SamodelkinCharacter> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfRace = CursorUtil.getColumnIndexOrThrow(_cursor, "race");
          final int _cursorIndexOfProfession = CursorUtil.getColumnIndexOrThrow(_cursor, "profession");
          final int _cursorIndexOfDexterity = CursorUtil.getColumnIndexOrThrow(_cursor, "dexterity");
          final int _cursorIndexOfWisdom = CursorUtil.getColumnIndexOrThrow(_cursor, "wisdom");
          final int _cursorIndexOfStrength = CursorUtil.getColumnIndexOrThrow(_cursor, "strength");
          final int _cursorIndexOfIntelligence = CursorUtil.getColumnIndexOrThrow(_cursor, "intelligence");
          final int _cursorIndexOfConstitution = CursorUtil.getColumnIndexOrThrow(_cursor, "constitution");
          final int _cursorIndexOfCharisma = CursorUtil.getColumnIndexOrThrow(_cursor, "charisma");
          final int _cursorIndexOfAvatarAssetPath = CursorUtil.getColumnIndexOrThrow(_cursor, "avatar");
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final List<SamodelkinCharacter> _result = new ArrayList<SamodelkinCharacter>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final SamodelkinCharacter _item;
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpRace;
            if (_cursor.isNull(_cursorIndexOfRace)) {
              _tmpRace = null;
            } else {
              _tmpRace = _cursor.getString(_cursorIndexOfRace);
            }
            final String _tmpProfession;
            if (_cursor.isNull(_cursorIndexOfProfession)) {
              _tmpProfession = null;
            } else {
              _tmpProfession = _cursor.getString(_cursorIndexOfProfession);
            }
            final int _tmpDexterity;
            _tmpDexterity = _cursor.getInt(_cursorIndexOfDexterity);
            final int _tmpWisdom;
            _tmpWisdom = _cursor.getInt(_cursorIndexOfWisdom);
            final int _tmpStrength;
            _tmpStrength = _cursor.getInt(_cursorIndexOfStrength);
            final int _tmpIntelligence;
            _tmpIntelligence = _cursor.getInt(_cursorIndexOfIntelligence);
            final int _tmpConstitution;
            _tmpConstitution = _cursor.getInt(_cursorIndexOfConstitution);
            final int _tmpCharisma;
            _tmpCharisma = _cursor.getInt(_cursorIndexOfCharisma);
            final String _tmpAvatarAssetPath;
            if (_cursor.isNull(_cursorIndexOfAvatarAssetPath)) {
              _tmpAvatarAssetPath = null;
            } else {
              _tmpAvatarAssetPath = _cursor.getString(_cursorIndexOfAvatarAssetPath);
            }
            final UUID _tmpId;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfId);
            }
            _tmpId = __samodelkinTypeConverters.toUUID(_tmp);
            _item = new SamodelkinCharacter(_tmpName,_tmpRace,_tmpProfession,_tmpDexterity,_tmpWisdom,_tmpStrength,_tmpIntelligence,_tmpConstitution,_tmpCharisma,_tmpAvatarAssetPath,_tmpId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getCharacterById(final UUID id,
      final Continuation<? super SamodelkinCharacter> continuation) {
    final String _sql = "SELECT * FROM character WHERE id=(?)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final String _tmp = __samodelkinTypeConverters.fromUUID(id);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<SamodelkinCharacter>() {
      @Override
      public SamodelkinCharacter call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfRace = CursorUtil.getColumnIndexOrThrow(_cursor, "race");
          final int _cursorIndexOfProfession = CursorUtil.getColumnIndexOrThrow(_cursor, "profession");
          final int _cursorIndexOfDexterity = CursorUtil.getColumnIndexOrThrow(_cursor, "dexterity");
          final int _cursorIndexOfWisdom = CursorUtil.getColumnIndexOrThrow(_cursor, "wisdom");
          final int _cursorIndexOfStrength = CursorUtil.getColumnIndexOrThrow(_cursor, "strength");
          final int _cursorIndexOfIntelligence = CursorUtil.getColumnIndexOrThrow(_cursor, "intelligence");
          final int _cursorIndexOfConstitution = CursorUtil.getColumnIndexOrThrow(_cursor, "constitution");
          final int _cursorIndexOfCharisma = CursorUtil.getColumnIndexOrThrow(_cursor, "charisma");
          final int _cursorIndexOfAvatarAssetPath = CursorUtil.getColumnIndexOrThrow(_cursor, "avatar");
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final SamodelkinCharacter _result;
          if(_cursor.moveToFirst()) {
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpRace;
            if (_cursor.isNull(_cursorIndexOfRace)) {
              _tmpRace = null;
            } else {
              _tmpRace = _cursor.getString(_cursorIndexOfRace);
            }
            final String _tmpProfession;
            if (_cursor.isNull(_cursorIndexOfProfession)) {
              _tmpProfession = null;
            } else {
              _tmpProfession = _cursor.getString(_cursorIndexOfProfession);
            }
            final int _tmpDexterity;
            _tmpDexterity = _cursor.getInt(_cursorIndexOfDexterity);
            final int _tmpWisdom;
            _tmpWisdom = _cursor.getInt(_cursorIndexOfWisdom);
            final int _tmpStrength;
            _tmpStrength = _cursor.getInt(_cursorIndexOfStrength);
            final int _tmpIntelligence;
            _tmpIntelligence = _cursor.getInt(_cursorIndexOfIntelligence);
            final int _tmpConstitution;
            _tmpConstitution = _cursor.getInt(_cursorIndexOfConstitution);
            final int _tmpCharisma;
            _tmpCharisma = _cursor.getInt(_cursorIndexOfCharisma);
            final String _tmpAvatarAssetPath;
            if (_cursor.isNull(_cursorIndexOfAvatarAssetPath)) {
              _tmpAvatarAssetPath = null;
            } else {
              _tmpAvatarAssetPath = _cursor.getString(_cursorIndexOfAvatarAssetPath);
            }
            final UUID _tmpId;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfId);
            }
            _tmpId = __samodelkinTypeConverters.toUUID(_tmp_1);
            _result = new SamodelkinCharacter(_tmpName,_tmpRace,_tmpProfession,_tmpDexterity,_tmpWisdom,_tmpStrength,_tmpIntelligence,_tmpConstitution,_tmpCharisma,_tmpAvatarAssetPath,_tmpId);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, continuation);
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
